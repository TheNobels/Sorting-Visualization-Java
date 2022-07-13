import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;

/*
  Should handle GUI, you can also create other class for GUI to clean up the code. 
  Explain your design choice.
*/
public class MyFrame extends JFrame {
  JFrame frame = new JFrame("Sorting Alogrithm Visualiser");
  JPanel startPanel = new JPanel();
  JPanel secondPanel = new JPanel();

  JTextField userInput = new JTextField();
  JLabel welcomeLabel, welcomeLabel1, instruction, command, userInputLabel, blankLabel;
  JLabel[] barArr;
  JButton bubbleSortButton, selectionSortButton, mergeSortButton, runButton, back;
  JButton startAnimation = new JButton("Start");
  JButton replayAnimation = new JButton("Replay");

  Paint paint = new Paint();

  Queue<Integer> numsArr = new LinkedList<>();
  String algo = "";
  int[] numberArr;
  int i = 0;

  private boolean isBubble = false;
  private boolean isSelection = false;
  private boolean isMerge = false;

  private BubbleSort bubbleSort;
  private SelectionSort selectionSort;
  private MergeSort mergeSort;

  private boolean isRunning;

  // Create a standard frame for the game
  MyFrame() {

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setSize(800, 800);

    welcomeLabel = new JLabel(
        "Welcome to Sorting Algorithm Visualiser.Please select one of the sorting algorithm below:",
        SwingConstants.CENTER);
    welcomeLabel1 = new JLabel("Please select one of the sorting algorithm below:", SwingConstants.CENTER);
    welcomeLabel.setPreferredSize(new Dimension(800, 100));
    welcomeLabel1.setPreferredSize(new Dimension(800, 100));

    bubbleSortButton = new JButton("Bubble Sort");
    bubbleSortButton.setPreferredSize(new Dimension(200, 50));
    selectionSortButton = new JButton("Selection Sort");
    selectionSortButton.setPreferredSize(new Dimension(200, 50));
    mergeSortButton = new JButton("Merge Sort");
    mergeSortButton.setPreferredSize(new Dimension(200, 50));

    bubbleSortButton.setFocusable(false);
    selectionSortButton.setFocusable(false);
    mergeSortButton.setFocusable(false);

    bubbleSortButton.addActionListener(new ButtonListener());
    selectionSortButton.addActionListener(new ButtonListener());
    mergeSortButton.addActionListener(new ButtonListener());

    startPanel.add(welcomeLabel);
    startPanel.add(welcomeLabel1);
    startPanel.add(bubbleSortButton);
    startPanel.add(selectionSortButton);
    startPanel.add(mergeSortButton);

    frame.add(startPanel);
    frame.setVisible(true);
  }

  // User input the numbers they want to see
  void secondPage() {

    instruction = new JLabel("Enter numbers of any amount between -330 to 330:", SwingConstants.CENTER);
    instruction.setPreferredSize(new Dimension(800, 100));

    command = new JLabel("PRESS ENTER AFTER EACH NUMBER INPUT!", SwingConstants.CENTER);
    command.setPreferredSize(new Dimension(800,100));

    userInput.setPreferredSize(new Dimension(200, 25));
    userInput.addKeyListener(new InputListener());

    blankLabel = new JLabel();
    blankLabel.setPreferredSize(new Dimension(600, 25));

    userInputLabel = new JLabel("Numbers enter: " + numsArr, SwingConstants.CENTER);
    userInputLabel.setPreferredSize(new Dimension(800, 100));

    runButton = new JButton("Run " + algo);
    runButton.setPreferredSize(new Dimension(200, 50));
    runButton.setFocusable(false);
    runButton.addActionListener(new ButtonListener());

    secondPanel.add(instruction);
    secondPanel.add(command);
    secondPanel.add(userInput);
    secondPanel.add(blankLabel);
    secondPanel.add(runButton);
    secondPanel.add(userInputLabel);

    frame.add(secondPanel);

  }

  // Runs the animations
  void thirdPage() {

    bubbleSort = new BubbleSort(numberArr);
    selectionSort = new SelectionSort(numberArr);
    mergeSort = new MergeSort(numberArr);

    back = new JButton("Main Menu");
    back.setPreferredSize(new Dimension(200, 50));
    back.addActionListener(new ButtonListener());
    back.setFocusable(false);

    startAnimation.setPreferredSize(new Dimension(200, 50));
    startAnimation.setFocusable(false);
    startAnimation.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          if (isRunning == false)
            isRunning = true;
          animate();
        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }
    });

    paint.add(back);
    paint.add(startAnimation);
    frame.getContentPane().add(paint);

  }

  // Method to run the visual sorting
  private void animate() throws Exception {
    if (algo == "BubbleSort") {
      isBubble = true;
    } else if (algo == "SelectionSort") {
      isSelection = true;
    } else {
      isMerge = true;
    }

    if (isBubble) {

      bubbleSort.setCompareIndex(0);

      Timer bubble = new Timer(30, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

          if (sorted() || isRunning == false) {
            bubbleSort.setCompareIndex(Integer.MAX_VALUE);
            isRunning = false;
            ((Timer) e.getSource()).stop();
          } else {
            if (isRunning == true)
              numberArr = bubbleSort.performBubbleSort();
          }
          paint.repaint();
        }
      });

      bubble.start();

    }

    if (isSelection) {

      selectionSort.setArrayIndex(0);
      selectionSort.setCompareIndex(1);
      selectionSort.setMinIdx(0);

      Timer select = new Timer(30, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

          if (sorted() || isRunning == false) {
            selectionSort.setCompareIndex(Integer.MAX_VALUE);
            selectionSort.setArrayIndex(Integer.MAX_VALUE);
            selectionSort.setMinIdx(Integer.MAX_VALUE);
            isRunning = false;
            ((Timer) e.getSource()).stop();
          } else {
            if (isRunning == true)
              numberArr = selectionSort.performSelectionSort();
          }
          paint.repaint();
        }
      });

      select.start();
    }

    if (isMerge) {

      Timer merge = new Timer(30, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

          if (sorted() || isRunning == false) {
            mergeSort.setArrayIndex(Integer.MAX_VALUE);
            mergeSort.setCompareIndex(Integer.MAX_VALUE);
            isRunning = false;
            ((Timer) e.getSource()).stop();
          } else {
            if (isRunning == true)
              numberArr = mergeSort.performMerge();
          }

          paint.repaint();
        }
      });

      merge.start();
    }

  }

  boolean sorted() {
    for (int i = 0; i < numberArr.length - 1; i++) {
      if (numberArr[i] > numberArr[i + 1]) {
        return false;
      }
    }

    return true;
  }

  // This class give buttons to do actions
  private class ButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
      if (event.getSource() == bubbleSortButton || event.getSource() == selectionSortButton
          || event.getSource() == mergeSortButton) {
        if (event.getSource() == bubbleSortButton) {
          algo = "BubbleSort";
          isBubble = true;
          isMerge = false;
          isSelection = false;
        } else if (event.getSource() == selectionSortButton) {
          algo = "SelectionSort";
          isSelection = true;
          isBubble = false;
          isMerge = false;
        } else {
          algo = "MergeSort";
          isMerge = true;
          isBubble = false;
          isSelection = false;
        }

        startPanel.setVisible(false);

        secondPage();
      }

      if (event.getSource() == runButton) {
        numberArr = new int[numsArr.size()];

        for (int i = 0; i < numberArr.length; i++) {
          numberArr[i] = numsArr.poll();
        }

        secondPanel.setVisible(false);
        thirdPage();
      }

      if (event.getSource() == back) {
        frame.dispose();
        new MyFrame();
      }

    }
  }

  // When user input the numbers into the prompt
  private class InputListener implements KeyListener {

    public void keyPressed(KeyEvent event) {
      if (event.getKeyCode() == KeyEvent.VK_ENTER) {

        try {
          int num = Integer.parseInt(userInput.getText());
          if (num >= -330 && num <= 330) {
            numsArr.add(num);
            userInput.setText(null);
            userInputLabel.setText("Numbers enter: " + numsArr);
          } else {
            userInput.setText(null);
            userInputLabel.setText("Please enter a number between -330 to 330");
          }
        } catch (Exception e) {
          userInput.setText(null);
          userInputLabel.setText("INVALID INPUT! Please enter a number between -330 to 330");
        }

      }

    }

    public void keyTyped(KeyEvent event) {

    }

    public void keyReleased(KeyEvent event) {

    }
  }

  private class Paint extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      this.setBackground(Color.BLACK);

      for (int i = 0; i < numberArr.length; i++) {

        String num = String.valueOf(numberArr[i]);

        g.setColor(Color.BLACK);

        if (numberArr[i] < 0) {
          g.drawRect((i * (800 / numberArr.length)), 400 - numberArr[i], (800 / numberArr.length) - 1,
              (400 - (numberArr[i] * -1)) / 2);
        } else if (numberArr[i] > 0) {
          g.drawRect((i * (800 / numberArr.length)), (800 - numberArr[i]) - 400, (800 / numberArr.length) - 1,
              (numberArr[i] + 400));
        } else {
          g.drawRect((i * (800 / numberArr.length)), 400, (800 / numberArr.length) - 1, 400);
        }

        if (isBubble) {

          if (i == bubbleSort.getCompareIndex() || i == (bubbleSort.getCompareIndex() + 1)) {
            g.setColor(Color.MAGENTA);
          }

        }

        if (isSelection) {

          if (i == selectionSort.getCompareIndex() || i == selectionSort.getMinIdx()) {
            g.setColor(Color.MAGENTA);
          }

          if (i == selectionSort.getArrayIndex()) {
            g.setColor(Color.GREEN);
          }
        }

        if (isMerge) {

          if (i == mergeSort.getArrayIndex()) {
            g.setColor(Color.MAGENTA);
          }

          if (i == mergeSort.getCompareIndex()) {
            g.setColor(Color.BLUE);
          }

        }

        if (g.getColor() != Color.MAGENTA && g.getColor() != Color.GREEN && g.getColor() != Color.BLUE) {
          if (numberArr[i] <= 0) {
            g.setColor(Color.PINK);
          } else {
            g.setColor(Color.CYAN);
          }
        }

        int center = (800 / numberArr.length) / 2;
        if (numberArr[i] < 0) {
          g.fillRect((i * (800 / numberArr.length)), 400 - numberArr[i], (800 / numberArr.length) - 1,
              (400 - (numberArr[i] * -1)) / 2);
          g.drawString(num, (i * (800 / numberArr.length)) + center, 400 - numberArr[i]);
        } else if (numberArr[i] > 0) {
          g.fillRect((i * (800 / numberArr.length)), (800 - numberArr[i]) - 400, (800 / numberArr.length) - 1,
              (numberArr[i] + 400));
          g.drawString(num, (i * (800 / numberArr.length)) + center, (800 - numberArr[i]) - 400);
        } else {
          g.fillRect((i * (800 / numberArr.length)), 400, (800 / numberArr.length) - 1, 400);
          g.drawString(num, (i * (800 / numberArr.length)) + center, 400);
        }

      }

    }
  }

}
