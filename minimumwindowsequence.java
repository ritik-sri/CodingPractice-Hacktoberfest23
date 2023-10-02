/*
Minimum Window Subsequence

Description:

Given strings S and T, return the minimum (contiguous) substring W of S, so that T is a subsequence of W. If there is no such window in S that covers all characters in T, return an empty string. If there are multiple such minimum-length windows, return the one with the left-most starting index.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Input: S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: "bcde" is the answer because it occurs before "bdde" which has the same length.
*/

class Solution {
    public String minWindow(String S, String T) {
        int sPointer = 0, tPointer = 0;
        int sLen = S.length(), tLen = T.length();
        int minLen = Integer.MAX_VALUE;
        int minStart = -1;
        
        while (sPointer < sLen) {
            if (S.charAt(sPointer) == T.charAt(tPointer)) {
                tPointer++;
                if (tPointer == tLen) {
                    int end = sPointer + 1; // end index of the window
                    tPointer--; // move tPointer back to the last matching character in T
                    while (tPointer >= 0) {
                        if (S.charAt(sPointer) == T.charAt(tPointer)) {
                            tPointer--;
                        }
                        sPointer--;
                    }
                    sPointer++; // move sPointer back to the first matching character in T
                    tPointer = 0; // reset tPointer
                    if (end - sPointer < minLen) {
                        minLen = end - sPointer;
                        minStart = sPointer;
                    }
                }
            }
            sPointer++;
        }
        
        return minStart == -1 ? "" : S.substring(minStart, minStart + minLen);
    }
}

/*
Explanation:

This solution uses two pointers to find the minimum window in the string S that contains all characters from string T. The sPointer points to the current character in S being processed, and the tPointer points to the current character in T being matched.

The algorithm iterates through the characters in S. When a character in S matches the character in T at tPointer, the tPointer is moved forward. When all characters in T have been matched, the window is found. Then, the sPointer and tPointer are adjusted to find the next possible window. The minimum length and starting index of the window are updated accordingly.

This solution has a time complexity of O(S + T) and a space complexity of O(1), where S and T are the lengths of strings S and T, respectively.
*/
