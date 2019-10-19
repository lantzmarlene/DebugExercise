# iebis_swdev_debugging
Source code to test debugging

## Instructions
First, **Fork** this project.

There are three exercises splitted in three branches of this repository. You must switch branches to checkout the code of each exercise.
Then, find the bugs that appear in each branch.
Fix the bugs if you can and answer to the questions proposed below.
Commit the code before checking out a different branch to avoid loosing the fixes that you have made to the code.

Once that you are done fixing bugs, **to score you must**:
1. Switch to the master branch.
2. Type below in this README.md file the answer to each question and paste some code that you have used to solve them.
3. Commit the changes
4. Push to your GitHub repository
5. Finally place a Pull Request so I can see your proposed answers


## Exercises
### Exercise 1
In this code there is a class called WordAnalyzer that contains several methods that analyzes some characteristics of the word passed as argument when the object WordAnalyzer is created.

For some reason, the methods are not working properly, sometimes they return the correct value and others don't. You need to answer the next questions.

#### a) Why the method _firstMultipleCharacter_ is returning "c" for the word _comprehensive_, when the correct answer should be "e"?

The firstMultipleCharacter method calls the find function which has two paramenters, a char and a position value. The position value remains constant an never moves forward, for that reason it stays at the first letter c. A way to fix this is to advance the position so as to not remain at the first position. 
    
        private int find(char c, int pos)
        {
            for (int i = pos+1; i < word.length(); i++)
            {
            
                if (word.charAt(i) == c)
                {
                    return i;
                }
            }
            return -1;
        }
    
#### b) Why the method _firstRepeatedCharacter_ is throwing an exception?
The firstRepeatedCharacter throws a StringIndexOutOfBoundsException. In essence it is attempting to fetch a value that is not there. The way to fix this is by reducing the word.length method by 1 to stay within the index.
 
       public char firstRepeatedCharacter()
        {
            for (int i = 0; i < word.length()-1; i++)
            {
                char ch = word.charAt(i);
                if (ch == word.charAt(i + 1))
                    return ch;
            }
            return 0;
        }
    
#### c) Why the method _countGroupsRepeatedCharacters_ returns 3 in one case when it should be 4?
The for loop initialises i at a value of 1 thereby ignoring the string character in position 0 which may or may not have an identical character adjacent to it. However, once the initial value was set to 0 it would return another StringIndexOutOfBoundsException. To circumvent this problem an additional if statement was added to test if i is equal to 0 so that it may go on to the next letter. 

    public int countGroupsRepeatedCharacters()
    {
        int c = 0;
        for (int i = 0; i < word.length() - 1; i++)
        {
            if (word.charAt(i) == word.charAt(i + 1)) // found a repetition
            {
                if(i == 0){
                    c++;
                    continue;
                }
                else if(word.charAt(i-1) != word.charAt(i)) {
                    c++;
                }
            }
        }
        return c;
    }

_**Strategy**: Place breakpoints before the methods are executed, step into them and see what happens._
### Exercise 2
In this code we are placing mines in a board game where we have several spaces that can be mined. 
The boards can contain _Element_ objects, and since _Space_ and _Mine_ inherits from _Element_, the boards can contain this kind as well.

We have two boards of different size and place a different number of mines on each one. But in the second case it takes longer to place all the mines.

#### a) Why placing less bombs takes longer in the second case?
The RED_BOARD is a larger int than that of the BLUE_BOARD moreover the values are set using the Random class. Because of the constraint it is harder to randomly plant the bombs in the BLUE_BOARD because the number of mines is similar to the amount of spaces there are. Randomly finding empty places to place a mine takes time. On the other hand the RED_BOARD takes less time because of essentially the opposite reason, a smaller number of mines in proportion to the board size means that it is easier to randomly find a place where the mine is able to be stashed. 

#### b) Knowing that usually there are going to be more bombs than spaces in the final boards, how would you change the method _minningTheBoard_ to be more efficient?
I'm not entirely sure what this question is asking but I feel like it would be more efficient to then assign every space as a bomb and then make free spaces from there. 

        public static void minningTheBoard(int numberMines) {
            Random random = new Random();
    
            for(int i = 0; i<myBoard.size(); i++){
                myBoard.put(i, new Mine());
            }
            int noOfSpaces = myBoard.size() - numberMines;
            while (noOfSpaces > 0) {
                Integer trial = new Integer(random.nextInt(myBoard.size()));
    
                if (myBoard.get(trial) instanceof Space) {
                    myBoard.put(trial, new Mine());
                    numberMines--;
                }
            }
        }

_**Strategy**: Understand well what the code does. Use conditionals breakpoints._


### Exercise 3
In this case this code looks really simple. When the "d" reaches the value 1.0, the program should end, but it never does.

#### a) Why does not appear a message indicating that "d is 1"?
I'm not entirely sure, but I can only assume that it has to do with the code not exiting the while loop. If we were to exit the while loop then the print function would print but because it is not there and there is no error message it is only logical to assume that we remain within the while loop.

#### b) How will you fix it?
To me it seems that decimals have a problem with java so I would just use normal int values. In the event that we need to use decimals then I really am unsure. 

        public class Main {
            public static void main(String [] args) {
                int d = 0;
    
                while (d != 10) {
                    d += 1;
                }
    
                System.out.println("d is 1");
            }
        }

###### _P.S. Please send help... I don't understand most of this and had to use google every 3 seconds to understand things._