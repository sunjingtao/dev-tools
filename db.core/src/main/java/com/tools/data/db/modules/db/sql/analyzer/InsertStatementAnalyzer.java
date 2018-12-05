/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
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
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2009 Sun Microsystems, Inc.
 */
package com.tools.data.db.modules.db.sql.analyzer;

import com.tools.lib.lexer.TokenSequence;
import com.tools.data.db.modules.db.core.SQLIdentifiers;
import com.tools.data.db.modules.db.sql.lexer.SQLTokenId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jiri Rechtacek
 */
class InsertStatementAnalyzer extends SQLStatementAnalyzer {

    private final List<String> columns = new ArrayList<String> ();
    private final List<String> values = new ArrayList<String> ();
    private QualIdent table = null;

    public static InsertStatement analyze (TokenSequence<SQLTokenId> seq, SQLIdentifiers.Quoter quoter) {
        seq.moveStart();
        if (!seq.moveNext()) {
            return null;
        }
        InsertStatementAnalyzer sa = new InsertStatementAnalyzer(seq, quoter);
        sa.parse();
        TableIdent ti = new TableIdent(sa.table, null);
        TablesClause tablesClause = sa.context.isAfter(SQLStatement.Context.FROM) ? sa.createTablesClause(Collections.singletonList(ti)) : null;
        return new InsertStatement(
                sa.startOffset, seq.offset() + seq.token().length(),
                sa.getTable(),
                Collections.unmodifiableList(sa.columns),
                Collections.unmodifiableList(sa.values),
                sa.offset2Context,
                tablesClause,
                Collections.unmodifiableList(sa.subqueries)
        );
    }

    private InsertStatementAnalyzer (TokenSequence<SQLTokenId> seq, SQLIdentifiers.Quoter quoter) {
        super(seq, quoter);
    }

    private void parse () {
        startOffset = seq.offset ();
        do {
            switch (context) {
                case START:
                    if (SQLStatementAnalyzer.isKeyword ("INSERT", seq)) { // NOI18N
                        moveToContext(SQLStatement.Context.INSERT);
                    }
                    break;
                case INSERT:
                    if (SQLStatementAnalyzer.isKeyword("INTO", seq)) { // NOI18N
                        moveToContext(SQLStatement.Context.INSERT_INTO);
                    }
                    break;
                case INSERT_INTO:
                    switch (seq.token ().id ()) {
                        case IDENTIFIER:
                            table = parseIdentifier();
                            break;
                        case LPAREN:
                            moveToContext(SQLStatement.Context.COLUMNS);
                            break;
                        case KEYWORD:
                            if (SQLStatementAnalyzer.isKeyword ("VALUES", seq)) {  //NOI18N
                                moveToContext(SQLStatement.Context.VALUES);
                            } else if (SQLStatementAnalyzer.isKeyword ("SET", seq)) {  //NOI18N
                                moveToContext(SQLStatement.Context.SET);
                            }
                            break;
                    }
                    break;
                case COLUMNS:
                    switch (seq.token ().id ()) {
                        case IDENTIFIER:
                            List<String> chosenColumns = analyzeChosenColumns ();
                            if ( ! chosenColumns.isEmpty ()) {
                                columns.addAll (chosenColumns);
                            }
                            break;
                        case KEYWORD:
                            if (SQLStatementAnalyzer.isKeyword ("VALUES", seq)) {  //NOI18N
                                moveToContext(SQLStatement.Context.VALUES);
                            }
                            break;
                        case RPAREN:
                            moveToContext(SQLStatement.Context.VALUES);
                            break;
                    }
                    break;
                case VALUES:
                    switch (seq.token ().id ()) {
                        case IDENTIFIER:
                            List<String> newValues = analyzeChosenColumns ();
                            if ( ! newValues.isEmpty ()) {
                                values.addAll (newValues);
                            }
                            break;
                    }
                    break;
                default:
            }
        } while (nextToken ());
    }

    private List<String> analyzeChosenColumns () {
        List<String> parts = new ArrayList<String> ();
        parts.add (getUnquotedIdentifier ());
        while (seq.moveNext ()) {
            switch (seq.token ().id ()) {
                case WHITESPACE:
                    continue;
                case COMMA:
                    continue;
                case RPAREN:
                    return parts;
                default:
                    parts.add (getUnquotedIdentifier ());
            }
        }
        return parts;
    }

    private QualIdent getTable () {
        return table;
    }
}