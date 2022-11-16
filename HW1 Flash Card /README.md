# Homework 1

The instructions can be found in `README.md` in the `main` branch. 

## Note on Travis/Checkstyle

You might notice that the Travis build fails right now, due to a checkstyle error. This is due to the `// TODO` comment in `Main.java`; it will succeed when that comment is removed. You will probably see builds fail quite a bit more often during this course, as you push your own code. Keep an eye on this; it is often a helpful signal that something is broken or imperfect, so learning to read the logs to figure out why it failed is a helpful skill. Naturally, the build should not fail when you turn in your assignments.

##Three Achievements:
Achievement 1: all cards are answered correctly in the last round.
Achievement 2: cards have been answered for at least 5 rounds. 
Achievement 3: there is at least one card that has been answered correctly for three consecutive rounds

##Testing Strategy
Actually, I did not use any testing strategies such as boundary value analysis because I think, there is no boundary value issues in all the units that we have to test. I just selected valid and invalid inputs to test whether the implementation matches specification.


##Specification vs structure testing
Specification Testing: We only have to care about whether the implementations have done the specifications without knowing the specific implementations. I think, it is easy to come up with some test cases, but on the downside, it will be easy for us to overlook some edge cases, such as null input, off-by-one and etc. Therefore, we have to pay special attention to use some strategies to make sure the codes works well in edge cases.

Structure Testing: the upside of structure testing is that it is not easy to overlook any edge case since we strive to achieve 100% branch coverage in structure testing. If we overlook some cases, it will never achieve 100% branch coverage and we can know which branch hasn't covered by reading the report. The downside of structure testing is that we have to write a lot of test case, which may be time-consuming and requires a lot of coding.

Personally speaking, I think structure testing is easier to write, because it is easy to find which part of the code we haven't tested by referencing to our codes.