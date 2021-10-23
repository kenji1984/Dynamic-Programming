# Dynamic-Programming
My stab at Dynamic Programming. After many stabs at trying, including joining a Master class I couldn't get it.
Every *different* tutorial is using the same explanation, the same Mathematical equations best[i,j] = best(i...k)+ best(k+1....j) blah blah to describe it. But you just can't beat a dead horse.

This time, I did not try to understand the Algorithms, but look at an existing solution written in Java to see what its intention is. I think I've got the hang of it!

Although, I'm not sure if it is Dynamic Programming. It seems like BruteForce. BruteForce examines *every* scenario of the problem where I'm just breaking it down, then examine *every* scenario. Ultimately, I'm still looking at every scenario. However, from my understanding, the caching makes *my* every scenario different from *BruteForce*'s every scenario.

The idea is:
1. Start the problem at the simplest case possible.
2. When problem is simple, it is easy to bruteforce every possible scenario to get the answer.
3. Save this scenario. For example, in a problem on how to best spend $100, start with $1, and save the best result as result[1] = best solution. (Think result[1] == result[$1]).
4. Slowly increase the problem size and do the same thing, and bruteforce every possible scenarios to get the answer, and so on...
5. You will find that saving result[$1] may help you with finding result[$2] and saving result[$2] may help you with finding result[$8].
6. Even when the problem starts getting large, bruteforcing scenarios is still very easy because you have save the result for every previous steps.

I find it working against so many problems, 1D or 2D. I am not interested in competitive programming and there's no point to doing so many problems over and over.
If I see something interesting on my social feed, I do try to solve it using the same strategy. So far, it seems to work except for finding the combination of every words from a number of characters.