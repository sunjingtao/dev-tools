/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */

package com.tools.lib.lexer.internal.inc;

import com.tools.lib.lexer.InputAttributes;
import com.tools.lib.lexer.Language;
import com.tools.lib.lexer.LanguagePath;
import com.tools.lib.lexer.TokenId;
import com.tools.lib.lexer.internal.*;
import com.tools.lib.lexer.internal.token.AbstractToken;
import com.tools.lib.lexer.internal.token.TextToken;
import com.tools.data.db.util.CompactMap;

import java.util.Set;


/**
 * Token list used by token hierarchy snapshot.
 *
 * @author Miloslav Metelka
 * @version 1.00
 */

public final class SnapshotTokenList<T extends TokenId> implements TokenList<T> {

    /** Due to debugging purposes - dumpInfo() use. */
    private TokenHierarchyOperation<?,T> snapshot;

    private IncTokenList<T> liveTokenList;

    private int liveTokenGapStart = -1;

    private int liveTokenGapEnd;

    private int liveTokenGapStartOffset;

    private int liveTokenOffsetDiff;

    /** Captured original tokens or branches. */
    private TokenOrEmbedding<T>[] origTokenOrEmbeddings;

    /** Original token's offsets. The array is occupied
     * and maintained in the same way like origTokensOrBranches.
     */
    private int[] origOffsets;

    /** Index where tokens start in tokens array. */
    private int origTokenStartIndex;

    /** Number of original tokens. */
    private int origTokenCount;

    /** Overrides of tokens' offset. */
    private CompactMap<AbstractToken<T>, Token2OffsetEntry<T>> token2offset;

    public int liveTokenGapStart() {
        return liveTokenGapStart;
    }

    public int liveTokenGapEnd() {
        return liveTokenGapEnd;
    }
    
    public SnapshotTokenList(TokenHierarchyOperation<?,T> snapshot) {
        this.snapshot = snapshot;
//        this.liveTokenList = (IncTokenList<T>)snapshot.
//                liveTokenHierarchyOperation().rootTokenList();
        token2offset = new CompactMap<AbstractToken<T>,Token2OffsetEntry<T>>();
    }

    public TokenHierarchyOperation<?,T> snapshot() {
        return snapshot;
    }

    @Override
    public Language<T> language() {
        return liveTokenList.language();
    }
    
    @Override
    public LanguagePath languagePath() {
        return liveTokenList.languagePath();
    }
    
    @Override
    public TokenOrEmbedding<T> tokenOrEmbedding(int index) {
        if (liveTokenGapStart == -1 || index < liveTokenGapStart) {
            return liveTokenList.tokenOrEmbedding(index);
        }
        index -= liveTokenGapStart;
        if (index < origTokenCount) {
            return origTokenOrEmbeddings[origTokenStartIndex + index];
        }
        return liveTokenList.tokenOrEmbedding(liveTokenGapEnd + index - origTokenCount);
    }

    @Override
    public int lookahead(int index) {
        // Lookahead not supported for certain snapshot's tokens
        // so better don't return it for any of them.
        return -1;
    }

    @Override
    public Object state(int index) {
        // Lookahead not supported for certain snapshot's tokens
        // so better don't return it for any of them.
        return null;
    }

    @Override
    public int tokenOffset(int index) {
        if (liveTokenGapStart == -1 || index < liveTokenGapStart) {
            return liveTokenList.tokenOffset(index);
        }
        index -= liveTokenGapStart;
        if (index < origTokenCount) {
            return origOffsets[origTokenStartIndex + index];
        }
        index -= origTokenCount;

        AbstractToken<T> token = liveTokenList.tokenOrEmbeddingDirect(liveTokenGapEnd + index).token();
        int offset;
        if (token.isFlyweight()) {
            offset = token.length();
            while (--index >= 0) {
                token = liveTokenList.tokenOrEmbeddingDirect(liveTokenGapEnd + index).token();
                if (token.isFlyweight()) {
                    offset += token.length();
                } else { // non-flyweight element
                    offset += tokenOffset(token, liveTokenList);
                    break;
                }
            }
            if (index == -1) { // below the boundary of above-gap live tokens
                index += liveTokenGapStart + origTokenCount;
                if (index >= 0) {
                    offset += tokenOffset(index);
                }
            }
            
        } else { // non-flyweight
            offset = tokenOffset(token, liveTokenList);
        }
        return offset;
    }

    @Override
    public int[] tokenIndex(int offset) {
        return LexerUtilsConstants.tokenIndexLazyTokenCreation(this, offset);
    }

    /**
     * @param token non-null token for which the offset is being computed.
     * @param tokenList non-null token list to which the token belongs.
     * @return offset for the particular token.
     */
    public <TT extends TokenId> int tokenOffset(
    AbstractToken<TT> token, TokenList<TT> tokenList) {
        // The following situations can happen:
        // 1. Token instance is contained in token2offset map so the token's
        //    offset is overriden by the information in the map.
        // 2. Token instance is not contained in token2offset map
        //    and the token's tokenList is IncTokenList. In that case
        //    it needs to be checked whether the regularly computed offset
        //    is above liveTokenGapStartOffset and if so then
        //    liveTokenOffsetDiff must be added to it.
        // 3. Token instance is not contained in token2offset map
        //    and the token's tokenList is not IncTokenList.
        //    It happens for removed tokens that were removed but
        //    do not need the offset correction.
        //    In that case the computed offset is simply returned.
        // 4. Token from branch token list is passed.
        //    In this case the offset of the corresponding rootBranchToken
        //    needs to be corrected if necessary.
        if (tokenList.getClass() == EmbeddedTokenList.class) {
            EmbeddedTokenList<?,TT> etl = (EmbeddedTokenList<?,TT>)tokenList;
            AbstractToken<?> rootBranchToken = null; // originally etl.rootToken();
            Token2OffsetEntry<T> entry = token2offset.get(rootBranchToken);
            if (entry != null) {
                return entry.offset();// used to be: + etl.childTokenOffsetShift(rawOffset);
            } else { // no special entry => check whether the regular offset is below liveTokenGapStartOffset
                int offset = etl.tokenOffset(token);
                TokenList rootTokenList = etl.rootTokenList();
                if (rootTokenList != null && rootTokenList.getClass() == IncTokenList.class) {
                    if (offset >= liveTokenGapStartOffset) {
                        offset += liveTokenOffsetDiff;
                    }
                }
                return offset;
            }

        } else { // queried token list is the root list genericsed by <T>
            @SuppressWarnings("unchecked")
            Token2OffsetEntry<T> entry = token2offset.get((AbstractToken<T>)token);
            if (entry != null) {
                return entry.offset();
            } else {
                int offset = tokenList.tokenOffset(token);
                if (tokenList.getClass() == IncTokenList.class) {
                    if (offset >= liveTokenGapStartOffset) {
                        offset += liveTokenOffsetDiff;
                    }
                }
                return offset;
            }
        }
    }
    
    @Override
    public int tokenCount() {
        return (liveTokenGapStart == -1)
                ? liveTokenList.tokenCount()
                : liveTokenList.tokenCount() - (liveTokenGapEnd - liveTokenGapStart)
                    + origTokenCount;
    }

    @Override
    public int tokenCountCurrent() {
        return (liveTokenGapStart == -1)
                ? liveTokenList.tokenCountCurrent()
                : liveTokenList.tokenCountCurrent() - (liveTokenGapEnd - liveTokenGapStart)
                    + origTokenCount;
    }

    @Override
    public int modCount() {
        return LexerUtilsConstants.MOD_COUNT_IMMUTABLE_INPUT;
    }
    
    @Override
    public int tokenOffset(AbstractToken<T> token) {
        int rawOffset = token.rawOffset();
        // Offset of the standalone token is absolute
        return rawOffset;
    }
    
    public char charAt(int offset) {
        // No tokens expected to be parented to this token list
        throw new IllegalStateException("Not expected to be called"); // NOI18N
    }

    @Override
    public void setTokenOrEmbedding(int index, TokenOrEmbedding<T> t) {
        // Allow branching
        if (liveTokenGapStart == -1 || index < liveTokenGapStart) {
            liveTokenList.setTokenOrEmbedding(index, t);
        } else {
            index -= liveTokenGapStart;
            if (index < origTokenCount) {
                origTokenOrEmbeddings[origTokenStartIndex + index] = t;
            } else {
                liveTokenList.setTokenOrEmbedding(liveTokenGapEnd + index - origTokenCount, t);
            }
        }
    }

    @Override
    public AbstractToken<T> replaceFlyToken(int index, AbstractToken<T> flyToken, int offset) {
        AbstractToken<T> nonFlyToken;
        if (liveTokenGapStart == -1 || index < liveTokenGapStart) {
            nonFlyToken = liveTokenList.replaceFlyToken(index, flyToken, offset);
        } else {
            index -= liveTokenGapStart;
            if (index < origTokenCount) {
                nonFlyToken = ((TextToken<T>)flyToken).createCopy(this, offset);
                origTokenOrEmbeddings[origTokenStartIndex + index] = nonFlyToken;
            } else {
                nonFlyToken = liveTokenList.replaceFlyToken(
                        liveTokenGapEnd + index - origTokenCount,
                        flyToken, offset - liveTokenOffsetDiff);
            }
        }
        return nonFlyToken;
    }
    
    @Override
    public TokenList<?> rootTokenList() {
        return this;
    }

    @Override
    public CharSequence inputSourceText() {
        return rootTokenList().inputSourceText();
    }

    @Override
    public TokenHierarchyOperation<?,?> tokenHierarchyOperation() {
        return snapshot;
    }
    
    @Override
    public InputAttributes inputAttributes() {
        return liveTokenList.inputAttributes();
    }

    @Override
    public boolean isContinuous() {
        return true;
    }

    @Override
    public Set<T> skipTokenIds() {
        return null;
    }

    @Override
    public int startOffset() {
        if (tokenCountCurrent() > 0 || tokenCount() > 0)
            return tokenOffset(0);
        return 0;
    }

    @Override
    public int endOffset() {
        int cntM1 = tokenCount() - 1;
        if (cntM1 >= 0)
            return tokenOffset(cntM1) + tokenOrEmbedding(cntM1).token().length();
        return 0;
    }
    
    @Override
    public boolean isRemoved() {
        return false;
    }

    public boolean canModifyToken(int index, AbstractToken token) {
        return liveTokenGapStart != -1
                && index >= liveTokenGapStart
                && index < liveTokenGapEnd
                && !token2offset.containsKey(token);
    }

    public void update(TokenHierarchyEventInfo eventInfo, TokenListChange<T> change) {
        TokenList<T> removedTokenList = change.tokenChangeInfo().removedTokenList();
        int startRemovedIndex = change.index();
        int endRemovedIndex = startRemovedIndex + removedTokenList.tokenCount();
        if (liveTokenGapStart == -1) { // no modifications yet
            liveTokenGapStart = startRemovedIndex;
            liveTokenGapEnd = startRemovedIndex;
            liveTokenGapStartOffset = change.offset();
            @SuppressWarnings("unchecked")
            TokenOrEmbedding<T>[] tokenOrEmbeddings = new TokenOrEmbedding[removedTokenList.tokenCount()];
            origTokenOrEmbeddings = tokenOrEmbeddings;
            origOffsets = new int[origTokenOrEmbeddings.length];
        }

        int liveTokenIndexDiff = change.tokenChangeInfo().addedTokenCount()
                - removedTokenList.tokenCount();
        if (startRemovedIndex < liveTokenGapStart) { // will affect initial shared tokens
            int extraOrigTokenCount = liveTokenGapStart - startRemovedIndex;
            ensureOrigTokensStartCapacity(extraOrigTokenCount);
            origTokenStartIndex -= extraOrigTokenCount;
            origTokenCount += extraOrigTokenCount;

            int bound = Math.min(endRemovedIndex, liveTokenGapStart);
            int index;
            int offset = change.offset();
            liveTokenGapStartOffset = offset;
            for (index = startRemovedIndex; index < bound; index++) {
                TokenOrEmbedding<T> tokenOrEmbedding = removedTokenList.tokenOrEmbedding(index - startRemovedIndex);
                AbstractToken<T> token = tokenOrEmbedding.token();
                if (!token.isFlyweight()) {
                    TokenList<T> tokenList = token.tokenList();
                    if (tokenList == null) {
                        tokenList = null; // new StandaloneTokenList<T>(change.languagePath(),
                                // eventInfo.originalText().toCharArray(offset, offset + token.length()));
                        if (!token.isFlyweight())
                            token.setTokenList(tokenList);
                    }
                }
                origOffsets[origTokenStartIndex] = offset;
                origTokenOrEmbeddings[origTokenStartIndex++] = tokenOrEmbedding;
                offset += token.length();
            }

            while (index < liveTokenGapStart) {
                TokenOrEmbedding<T> tokenOrEmbedding = liveTokenList.tokenOrEmbeddingDirect(index + liveTokenIndexDiff);
                AbstractToken<T> t = tokenOrEmbedding.token();
                if (!t.isFlyweight()) {
                    token2offset.putEntry(new Token2OffsetEntry<T>(t, offset));
                }
                origOffsets[origTokenStartIndex] = offset;
                origTokenOrEmbeddings[origTokenStartIndex++] = tokenOrEmbedding;
                offset += t.length();
                index++;
            }
            liveTokenGapStart = startRemovedIndex;
        }

        if (endRemovedIndex > liveTokenGapEnd) { // will affect ending shared tokens
            int extraOrigTokenCount = endRemovedIndex - liveTokenGapEnd;
            ensureOrigTokensEndCapacity(extraOrigTokenCount);
            origTokenCount += extraOrigTokenCount;
            int origTokenIndex = origTokenStartIndex + origTokenCount - 1;

            int bound = Math.max(startRemovedIndex, liveTokenGapEnd);
            int index = endRemovedIndex;
            int offset = change.removedEndOffset();
            for (index = endRemovedIndex - 1; index >= bound; index--) {
                TokenOrEmbedding<T> tokenOrEmbedding = removedTokenList.tokenOrEmbedding(index - startRemovedIndex);
                AbstractToken<T> token = tokenOrEmbedding.token();
                offset -= token.length();
                if (!token.isFlyweight()) {
                    TokenList<T> tokenList = token.tokenList();
                    if (tokenList == null) {
                        tokenList = null; // new StandaloneTokenList<T>(change.languagePath(),
                                // eventInfo.originalText().toCharArray(offset, offset + token.length()));
                        if (!token.isFlyweight())
                            token.setTokenList(tokenList);
                    }
                }
                origOffsets[origTokenIndex] = offset + liveTokenOffsetDiff;
                // If the token's offset had to be diff-ed already then a map entry is necessary
                if (liveTokenOffsetDiff != 0) {
                    token2offset.putEntry(new Token2OffsetEntry<T>(token, origOffsets[origTokenIndex]));
                }
                origTokenOrEmbeddings[origTokenIndex--] = tokenOrEmbedding;
            }

            while (index >= liveTokenGapEnd) {
                TokenOrEmbedding<T> tokenOrEmbedding = liveTokenList.tokenOrEmbeddingDirect(index + liveTokenIndexDiff);
                AbstractToken<T> token = tokenOrEmbedding.token();
                offset -= token.length();
                if (!token.isFlyweight()) {
                    token2offset.putEntry(new Token2OffsetEntry<T>(token, offset));
                }
                origOffsets[origTokenIndex] = offset + liveTokenOffsetDiff;
                token2offset.putEntry(new Token2OffsetEntry<T>(token, origOffsets[origTokenIndex]));
                origTokenOrEmbeddings[origTokenIndex--] = tokenOrEmbedding;
                index--;
            }
            liveTokenGapEnd = endRemovedIndex;
        }

        liveTokenOffsetDiff += eventInfo.removedLength() - eventInfo.insertedLength();
        liveTokenGapEnd += liveTokenIndexDiff;
    }

    private void ensureOrigTokensStartCapacity(int extraOrigTokenCount) {
        if (extraOrigTokenCount > origTokenOrEmbeddings.length - origTokenCount) { // will need to reallocate
            // Could check for maximum possible token count (origTokenCount + below-and-above live token counts)
            // but would cause init of live tokens above gap which is undesirable
            @SuppressWarnings("unchecked")
            TokenOrEmbedding<T>[] newOrigTokensOrBranches = new TokenOrEmbedding[
                    (origTokenOrEmbeddings.length * 3 / 2) + extraOrigTokenCount];
            int[] newOrigOffsets = new int[newOrigTokensOrBranches.length];
            int newIndex = Math.max(extraOrigTokenCount, (newOrigTokensOrBranches.length
                    - (origTokenCount + extraOrigTokenCount)) / 2);
            System.arraycopy(origTokenOrEmbeddings, origTokenStartIndex,
                    newOrigTokensOrBranches, newIndex, origTokenCount);
            System.arraycopy(origOffsets, origTokenStartIndex,
                    newOrigOffsets, newIndex, origTokenCount);
            origTokenOrEmbeddings = newOrigTokensOrBranches;
            origOffsets = newOrigOffsets;
            origTokenStartIndex = newIndex;

        } else if (extraOrigTokenCount > origTokenStartIndex) { // only move
            // Move to the end of the array
            int newIndex = origTokenOrEmbeddings.length - origTokenCount;
            System.arraycopy(origTokenOrEmbeddings, origTokenStartIndex,
                    origTokenOrEmbeddings, newIndex, origTokenCount);
            System.arraycopy(origOffsets, origTokenStartIndex,
                    origOffsets, newIndex, origTokenCount);
            origTokenStartIndex = origTokenOrEmbeddings.length - origTokenCount;
        }
    }
    
    private void ensureOrigTokensEndCapacity(int extraOrigTokenCount) {
        if (extraOrigTokenCount > origTokenOrEmbeddings.length - origTokenCount) { // will need to reallocate
            // Could check for maximum possible token count (origTokenCount + below-and-above live token counts)
            // but would cause init of live tokens above gap which is undesirable
            @SuppressWarnings("unchecked")
            TokenOrEmbedding<T>[] newOrigTokensOrBranches = new TokenOrEmbedding[
                    (origTokenOrEmbeddings.length * 3 / 2) + extraOrigTokenCount];
            int[] newOrigOffsets = new int[newOrigTokensOrBranches.length];
            int newIndex = (newOrigTokensOrBranches.length
                    - (origTokenCount + extraOrigTokenCount)) / 2;
            System.arraycopy(origTokenOrEmbeddings, origTokenStartIndex,
                    newOrigTokensOrBranches, newIndex, origTokenCount);
            System.arraycopy(origOffsets, origTokenStartIndex,
                    newOrigOffsets, newIndex, origTokenCount);
            origTokenOrEmbeddings = newOrigTokensOrBranches;
            origOffsets = newOrigOffsets;
            origTokenStartIndex = newIndex;

        } else if (extraOrigTokenCount > origTokenOrEmbeddings.length - origTokenCount - origTokenStartIndex) { // only move
            // Move to the end of the array
            System.arraycopy(origTokenOrEmbeddings, origTokenStartIndex,
                    origTokenOrEmbeddings, 0, origTokenCount);
            System.arraycopy(origOffsets, origTokenStartIndex,
                    origOffsets, 0, origTokenCount);
            origTokenStartIndex = 0;
        }
    }

    @Override
    public String toString() {
        return "liveTokenGapStart=" + liveTokenGapStart +
                ", liveTokenGapEnd=" + liveTokenGapEnd +
                ", liveTokenGapStartOffset=" + liveTokenGapStartOffset +
                ", liveTokenOffsetDiff=" + liveTokenOffsetDiff +
                ",\n origTokenStartIndex=" + origTokenStartIndex +
                ", origTokenCount=" + origTokenCount +
                ", token2offset: " + token2offset;

    }

    public int tokenShiftStartOffset() {
        return liveTokenGapStartOffset;
    }
    
    public int tokenShiftEndOffset() {
        return liveTokenGapStartOffset + liveTokenOffsetDiff;
    }

    @Override
    public StringBuilder dumpInfo(StringBuilder sb) {
        return sb;
    }

    @Override
    public String dumpInfoType() {
        return "SnapshotTL";
    }

    private static final class Token2OffsetEntry<T extends TokenId>
    extends CompactMap.MapEntry<AbstractToken<T>,Token2OffsetEntry<T>> {
        
        private final AbstractToken<T> token; // 20 bytes (16-super + 4)
        
        private final int offset; // 24 bytes
        
        Token2OffsetEntry(AbstractToken<T> token, int offset) {
            this.token = token;
            this.offset = offset;
        }
        
        @Override
        public AbstractToken<T> getKey() {
            return token;
        }

        @Override
        public Token2OffsetEntry<T> getValue() {
            return this;
        }
        
        @Override
        protected int valueHashCode() {
            return offset;
        }

        @Override
        protected boolean valueEquals(Object value2) {
            // In fact the second entry would have to be of <T> because
            // the tokens (as keys) must be the same objects to be equal
            return (value2 instanceof Token2OffsetEntry
                    && ((Token2OffsetEntry<?>)value2).offset() == offset());
        }
        
        public int offset() {
            return offset;
        }

        @Override
        public Token2OffsetEntry<T> setValue(Token2OffsetEntry<T> value) {
            throw new IllegalStateException("Prohibited"); // NOI18N
        }

        @Override
        public String toString() {
            // Debug the offset being held
            return String.valueOf(offset);
        }

    }
    
}
