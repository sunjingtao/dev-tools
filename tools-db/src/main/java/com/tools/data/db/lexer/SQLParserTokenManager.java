/* Generated By:JavaCC: Do not edit this line. SQLParserTokenManager.java */
package com.tools.data.db.lexer;

/** Token Manager. */
public class SQLParserTokenManager implements SQLParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x2L) != 0L)
            return 25;
         if ((active0 & 0x3ffffffe0L) != 0L)
         {
            jjmatchedKind = 35;
            return 5;
         }
         return -1;
      case 1:
         if ((active0 & 0x3e3fffac0L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 35;
               jjmatchedPos = 1;
            }
            return 5;
         }
         if ((active0 & 0x1c000520L) != 0L)
            return 5;
         return -1;
      case 2:
         if ((active0 & 0x2f13ff800L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 2;
            return 5;
         }
         if ((active0 & 0x102c003c0L) != 0L)
            return 5;
         return -1;
      case 3:
         if ((active0 & 0x2f10e5800L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 3;
            return 5;
         }
         if ((active0 & 0x31a000L) != 0L)
            return 5;
         return -1;
      case 4:
         if ((active0 & 0x81044000L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 4;
            return 5;
         }
         if ((active0 & 0x2700a1800L) != 0L)
            return 5;
         return -1;
      case 5:
         if ((active0 & 0x1004000L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 5;
            return 5;
         }
         if ((active0 & 0x80040000L) != 0L)
            return 5;
         return -1;
      case 6:
         if ((active0 & 0x1000000L) != 0L)
            return 5;
         if ((active0 & 0x4000L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 6;
            return 5;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 32:
         return jjStartNfaWithStates_0(0, 1, 25);
      case 40:
         return jjStopAtPos(0, 45);
      case 41:
         return jjStopAtPos(0, 46);
      case 42:
         return jjStopAtPos(0, 43);
      case 44:
         return jjStopAtPos(0, 42);
      case 46:
         return jjStopAtPos(0, 44);
      case 63:
         return jjStopAtPos(0, 47);
      case 65:
      case 97:
         return jjMoveStringLiteralDfa1_0(0x3e0L);
      case 66:
      case 98:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 67:
      case 99:
         return jjMoveStringLiteralDfa1_0(0x1800L);
      case 68:
      case 100:
         return jjMoveStringLiteralDfa1_0(0x6000L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa1_0(0x18000L);
      case 71:
      case 103:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 72:
      case 104:
         return jjMoveStringLiteralDfa1_0(0x40000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 74:
      case 106:
         return jjMoveStringLiteralDfa1_0(0x200000L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa1_0(0x100000L);
      case 77:
      case 109:
         return jjMoveStringLiteralDfa1_0(0xc00000L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa1_0(0x3000000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa1_0(0x3c000000L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa1_0(0x40000000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa1_0(0x180000000L);
      case 87:
      case 119:
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x1440000L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x80102000L);
      case 72:
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x200000000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x40804000L);
      case 76:
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x40L);
      case 78:
      case 110:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(1, 26, 5);
         return jjMoveStringLiteralDfa2_0(active0, 0x80080L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x2200800L);
      case 82:
      case 114:
         if ((active0 & 0x8000000L) != 0L)
         {
            jjmatchedKind = 27;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x10029000L);
      case 83:
      case 115:
         if ((active0 & 0x20L) != 0L)
         {
            jjmatchedKind = 5;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x100L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x120010000L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L);
      case 89:
      case 121:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(1, 10, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 67:
      case 99:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(2, 8, 5);
         break;
      case 68:
      case 100:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(2, 7, 5);
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000L);
      case 69:
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000000L);
      case 70:
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
      case 71:
      case 103:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(2, 9, 5);
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000L);
      case 76:
      case 108:
         if ((active0 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(2, 6, 5);
         return jjMoveStringLiteralDfa3_0(active0, 0x80010000L);
      case 77:
      case 109:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(2, 32, 5);
         break;
      case 78:
      case 110:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(2, 23, 5);
         return jjMoveStringLiteralDfa3_0(active0, 0x80000L);
      case 79:
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x29000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x6000L);
      case 84:
      case 116:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(2, 25, 5);
         return jjMoveStringLiteralDfa3_0(active0, 0x21000000L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x800L);
      case 86:
      case 118:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000L);
      case 88:
      case 120:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(2, 22, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 67:
      case 99:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(3, 13, 5);
         break;
      case 69:
      case 101:
         return jjMoveStringLiteralDfa4_0(active0, 0xb0080000L);
      case 72:
      case 104:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000000L);
      case 73:
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000L);
      case 76:
      case 108:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(3, 16, 5);
         break;
      case 77:
      case 109:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(3, 15, 5);
         break;
      case 78:
      case 110:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(3, 21, 5);
         return jjMoveStringLiteralDfa4_0(active0, 0x800L);
      case 82:
      case 114:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000L);
      case 83:
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000L);
      case 84:
      case 116:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(3, 20, 5);
         return jjMoveStringLiteralDfa4_0(active0, 0x4000L);
      case 85:
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x1020000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 67:
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x80000000L);
      case 69:
      case 101:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(4, 33, 5);
         break;
      case 73:
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x4000L);
      case 78:
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x40000L);
      case 80:
      case 112:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(4, 17, 5);
         break;
      case 82:
      case 114:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(4, 19, 5);
         else if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(4, 28, 5);
         else if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(4, 29, 5);
         return jjMoveStringLiteralDfa5_0(active0, 0x1000000L);
      case 83:
      case 115:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(4, 12, 5);
         break;
      case 84:
      case 116:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(4, 11, 5);
         else if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(4, 30, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 65:
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000000L);
      case 71:
      case 103:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(5, 18, 5);
         break;
      case 78:
      case 110:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000L);
      case 84:
      case 116:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(5, 31, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 67:
      case 99:
         return jjMoveStringLiteralDfa7_0(active0, 0x4000L);
      case 76:
      case 108:
         if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(6, 24, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 84:
      case 116:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(7, 14, 5);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x1ff00000fffffffeL, 0xffffffffffffc000L, 0xffffffffL, 0x600000000000000L
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0x0L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec3 = {
   0x3fffffffffL, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec4 = {
   0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec5 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffL, 0x0L
};
static final long[] jjbitVec6 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x0L, 0x0L
};
static final long[] jjbitVec7 = {
   0x3fffffffffffL, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec8 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec9 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 28;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 41)
                        kind = 41;
                     jjCheckNAdd(17);
                  }
                  else if ((0x7000000000000000L & l) != 0L)
                  {
                     if (kind > 34)
                        kind = 34;
                  }
                  else if (curChar == 32)
                     jjAddStates(0, 1);
                  else if (curChar == 39)
                     jjCheckNAddStates(2, 4);
                  else if (curChar == 34)
                     jjCheckNAddTwoStates(7, 8);
                  else if (curChar == 36)
                  {
                     if (kind > 35)
                        kind = 35;
                     jjCheckNAdd(5);
                  }
                  else if (curChar == 33)
                     jjCheckNAdd(1);
                  if (curChar == 60)
                     jjCheckNAddTwoStates(1, 19);
                  else if (curChar == 62)
                     jjCheckNAdd(1);
                  break;
               case 1:
                  if (curChar == 61 && kind > 34)
                     kind = 34;
                  break;
               case 2:
                  if (curChar == 33)
                     jjCheckNAdd(1);
                  break;
               case 3:
                  if (curChar == 62)
                     jjCheckNAdd(1);
                  break;
               case 4:
                  if (curChar != 36)
                     break;
                  if (kind > 35)
                     kind = 35;
                  jjCheckNAdd(5);
                  break;
               case 5:
                  if ((0x3ff001000000000L & l) == 0L)
                     break;
                  if (kind > 35)
                     kind = 35;
                  jjCheckNAdd(5);
                  break;
               case 6:
                  if (curChar == 34)
                     jjCheckNAddTwoStates(7, 8);
                  break;
               case 7:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddTwoStates(7, 8);
                  break;
               case 8:
                  if (curChar == 34 && kind > 36)
                     kind = 36;
                  break;
               case 10:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjAddStates(5, 6);
                  break;
               case 12:
               case 13:
                  if (curChar == 39)
                     jjCheckNAddStates(2, 4);
                  break;
               case 14:
                  if (curChar == 39)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if ((0xffffff7fffffd9ffL & l) != 0L)
                     jjCheckNAddStates(2, 4);
                  break;
               case 16:
                  if (curChar == 39 && kind > 40)
                     kind = 40;
                  break;
               case 17:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 41)
                     kind = 41;
                  jjCheckNAdd(17);
                  break;
               case 18:
                  if (curChar == 60)
                     jjCheckNAddTwoStates(1, 19);
                  break;
               case 19:
                  if (curChar == 62 && kind > 34)
                     kind = 34;
                  break;
               case 20:
                  if (curChar == 32)
                     jjAddStates(0, 1);
                  break;
               case 21:
                  if (curChar == 32 && kind > 34)
                     kind = 34;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 25:
                  if ((0x20000000200L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 26;
                  else if ((0x100000001000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 24;
                  break;
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 35)
                        kind = 35;
                     jjCheckNAdd(5);
                  }
                  else if (curChar == 96)
                     jjCheckNAddTwoStates(10, 11);
                  break;
               case 4:
               case 5:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 35)
                     kind = 35;
                  jjCheckNAdd(5);
                  break;
               case 7:
                  jjAddStates(7, 8);
                  break;
               case 9:
                  if (curChar == 96)
                     jjCheckNAddTwoStates(10, 11);
                  break;
               case 10:
                  if ((0xfffffffeffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(10, 11);
                  break;
               case 11:
                  if (curChar == 96 && kind > 37)
                     kind = 37;
                  break;
               case 15:
                  jjAddStates(2, 4);
                  break;
               case 22:
                  if ((0x2000000020L & l) != 0L)
                     jjCheckNAdd(21);
                  break;
               case 23:
                  if ((0x80000000800L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 24:
                  if ((0x20000000200L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 26:
                  if ((0x400000004000L & l) != 0L)
                     jjCheckNAdd(21);
                  break;
               case 27:
                  if ((0x20000000200L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 5:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 35)
                     kind = 35;
                  jjCheckNAdd(5);
                  break;
               case 7:
                  if (jjCanMove_1(hiByte, i1, i2, l1, l2))
                     jjAddStates(7, 8);
                  break;
               case 10:
                  if (jjCanMove_1(hiByte, i1, i2, l1, l2))
                     jjAddStates(5, 6);
                  break;
               case 15:
                  if (jjCanMove_1(hiByte, i1, i2, l1, l2))
                     jjAddStates(2, 4);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 28 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   25, 27, 14, 15, 16, 10, 11, 7, 8, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      case 45:
         return ((jjbitVec3[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec4[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 51:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 61:
         return ((jjbitVec7[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec9[i2] & l2) != 0L);
      default :
         if ((jjbitVec8[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, "\54", "\52", "\56", "\50", "\51", "\77", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xff3fffffffe1L, 
};
static final long[] jjtoSkip = {
   0x1eL, 
};
protected JavaCharStream input_stream;
private final int[] jjrounds = new int[28];
private final int[] jjstateSet = new int[56];
protected char curChar;
/** Constructor. */
public SQLParserTokenManager(JavaCharStream stream){
   if (JavaCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public SQLParserTokenManager(JavaCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 28; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 13 && (0x2600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}