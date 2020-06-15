package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Player
{
    String name;
    String sign;
    Player()
    {
        name="";
        sign="";
    }

}
class Computer
{
    String sign;
}
class GUI extends JFrame
{
    Player p;
    Computer c;
    JTextField t1;
    JLabel Name;
    JRadioButton rb1;
    JRadioButton rb2;
    JButton b;
    GUI()
    {
        p=new Player();
        c=new Computer();
        t1=new JTextField(20);
        Name=new JLabel("Name: ");
        rb1=new JRadioButton("X");
        rb2=new JRadioButton("O");
        b=new JButton("Play Game");
        ButtonGroup bg=new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        add(Name);
        add(t1);
        add(rb1);
        add(rb2);
        add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TIC TAC TOE HERE
                p.name=t1.getText();
                if(rb1.isSelected()) {
                    p.sign = "X";
                    c.sign="O";
                    TicTacToe t=new TicTacToe(p,c);
                    t.FirstMove();
                    dispose();
                }
                else if(rb2.isSelected())
                {
                    p.sign="O";
                    c.sign="X";
                    TicTacToe t=new TicTacToe(p,c);
                    t.FirstMove();
                    dispose();
                }

            }
        });
        setSize(400,400);
        setLayout(new FlowLayout());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class TicTacToe extends JFrame implements ActionListener
{
    Player p;
    Computer c;
    int n=4;      //size of board
    JButton [][]b;
    int EmptyCells=n*n;     //counter of total turns
    boolean PlayerTurn=false;
    boolean somebodyOne=false;
    int i,j;
    TicTacToe(Player player,Computer computer)
    {
        p=player;
        c=computer;
        b=new JButton[n][n];
        for(i=0;i<n;i++)
        {
            for(j=0;j<n;j++) {
                b[i][j] = new JButton("-");
                add(b[i][j]);
            }
        }

        for(i=0;i<n;i++)
        {
            for(j=0;j<n;j++) {
                b[i][j].addActionListener(this);
            }
        }

        setLayout(new GridLayout(n,n));
        setSize(400,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    boolean PlayHorizontal(int checkMarked)
    {
        int count=0;
        int index=-1;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(b[i][j].getText().equals(c.sign))
                {
                    count++;
                }
            }
            if(count==checkMarked)
            {
                index=i;
                break;
            }
            else
            {
                count=0;
            }
        }
        if(index!=-1)
        {
            for(int j=0;j<n;j++)
            {
                if(b[index][j].getText().equals("-"))
                {
                    b[index][j].setText(c.sign);
                    EmptyCells--;
                    PlayerTurn=true;
                    return true;
                }
            }
        }
        return false;
    }
    boolean PlayVertical(int checkMarked)
    {
        int count=0;
        int index=-1;
        for(int i=0;i<n;i++)        //columns
        {
            for(int j=0;j<n;j++)        //rows
            {
                if(b[j][i].getText().equals(c.sign))
                {
                    count++;
                }
            }
            if(count==checkMarked)
            {
                index=i;
                break;
            }
            else
            {
                count=0;
            }
        }
        if(index!=-1)
        {
            for(int j=0;j<n;j++)
            {
                if(b[j][index].getText().equals("-"))
                {
                    b[j][index].setText(c.sign);
                    EmptyCells--;
                    PlayerTurn=true;
                    return true;
                }
            }
        }
        return false;
    }
    boolean PlayDiagonally1(int checkMarked)
    {
        int count=0;
        for(int i=0;i<n;i++)
        {
            if(b[i][i].getText().equals(c.sign)) {
                count++;
            }
        }
        if(count==checkMarked)
        {
            for(int i=0;i<n;i++)
            {
                if(b[i][i].getText().equals("-"))
                {
                    b[i][i].setText(c.sign);
                    EmptyCells--;
                    PlayerTurn=true;
                    return true;
                }
            }
        }
        return false;
    }
    boolean PlayDiagonally2(int checkMarked)
    {
        int count=0;
        for (i = 0, j = n - 1; i < n && j >= 0; i++, j--)
        {
            if(b[i][j].getText().equals(c.sign)) {
                count++;
            }
        }
        if(count==checkMarked)
        {
            for (i = 0, j = n - 1; i < n && j >= 0; i++, j--)
            {
                if(b[i][j].getText().equals("-"))
                {
                    b[i][j].setText(c.sign);
                    EmptyCells--;
                    PlayerTurn=true;
                    return true;
                }
            }
        }
        return false;
    }
    boolean blockHorizontal(int checkMarked)
    {
        int count=0;
        int index=-1;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(b[i][j].getText().equals(p.sign))
                {
                    count++;
                }
            }
            if(count==checkMarked)
            {
                index=i;
                break;
            }
            else
            {
                count=0;
            }
        }
        if(index!=-1)
        {
            for(int i=0;i<n;i++)
            {
                if(b[index][i].getText().equals("-"))
                {
                    b[index][i].setText(c.sign);
                    EmptyCells--;
                    PlayerTurn=true;
                    return true;
                }
            }
        }
        return false;
    }
    boolean blockVertical(int checkMarked)
    {
        int count=0;
        int index=-1;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(b[j][i].getText().equals(p.sign))
                {
                    count++;
                }
            }
            if(count==checkMarked)
            {
                index=i;
                break;
            }
            else
            {
                count=0;
            }
        }
        if(index!=-1)
        {
            for(int i=0;i<n;i++)
            {
                if(b[i][index].getText().equals("-"))
                {
                    b[i][index].setText(c.sign);
                    EmptyCells--;
                    PlayerTurn=true;
                    return true;
                }
            }
        }
        return false;
    }
    boolean blockDiagonal1(int checkMarked)
    {
        int count=0;
        for(int i=0;i<n;i++)
        {
            if(b[i][i].getText().equals(p.sign))
            {
                count++;
            }
        }
        if(count==checkMarked)
        {
            for(int i=0;i<n;i++)
            {
                if(b[i][i].getText().equals("-"))
                {
                    b[i][i].setText(c.sign);
                    EmptyCells--;
                    PlayerTurn=true;
                    return true;
                }
            }
        }
        return false;
    }
    boolean blockDiagonal2(int checkMarked)
    {
        int count=0;
        for (i = 0, j = n - 1; i < n && j >= 0; i++, j--)
        {
            if(b[i][j].getText().equals(p.sign))
            {
                count++;
            }
        }
        if(count==checkMarked)
        {
            for (i = 0, j = n - 1; i < n && j >= 0; i++, j--)
            {
                if(b[i][j].getText().equals("-"))
                {
                    b[i][j].setText(c.sign);
                    EmptyCells--;
                    PlayerTurn=true;
                    return true;
                }
            }
        }
        return false;
    }
    void FirstMove()
    {
        int x = (int)(Math.random()*(n+1));
        int y = (int)(Math.random()*(n+1));
        if(x<0 || x>=n || y<0 || y>=n)
        {
            while(x<0 || x>=n || y<0 || y>=n)
            {
                x = (int)(Math.random()*(n+1));
                y = (int)(Math.random()*(n+1));
            }
        }
        b[x][y].setText(c.sign);
        EmptyCells--;
        PlayerTurn=true;
    }
    void Computerplay()
    {
        if(PlayerTurn!=true)
        {
                    if(PlayHorizontal(n)==true)
                    {
                        checkComputerWon();
                    }
                    else
                    {
                        if(PlayVertical(n)==true)
                        {
                            checkComputerWon();
                        }
                        else
                        {
                            if(PlayDiagonally1(n)==true)
                            {
                                checkComputerWon();
                            }
                            else
                            {
                                if(PlayDiagonally2(n)==true)
                                {
                                    checkComputerWon();
                                }
                                else
                                {
                                    if(blockHorizontal(n)==true)
                                    {
                                        checkComputerWon();
                                    }
                                    else
                                    {
                                        if(blockVertical(n)==true)
                                        {
                                            checkComputerWon();
                                        }
                                        else
                                        {
                                            if(blockDiagonal1(n)==true)
                                            {
                                                checkComputerWon();
                                            }
                                            else
                                            {
                                                if(blockDiagonal2(n)==true)
                                                {
                                                    checkComputerWon();
                                                }
                                                else
                                                {
                                                    if(PlayHorizontal(n-1)==true)
                                                    {
                                                        checkComputerWon();
                                                    }
                                                    else
                                                    {
                                                        if(PlayVertical(n-1)==true)
                                                        {
                                                            checkComputerWon();
                                                        }
                                                        else
                                                        {
                                                            if(PlayDiagonally1(n-1)==true)
                                                            {
                                                                checkComputerWon();
                                                            }
                                                            else
                                                            {
                                                                if(PlayDiagonally2(n-1)==true)
                                                                {
                                                                    checkComputerWon();
                                                                }
                                                                else
                                                                {
                                                                    if(blockHorizontal(n-1)==true)
                                                                    {
                                                                        checkComputerWon();
                                                                    }
                                                                    else
                                                                    {
                                                                        if(blockVertical(n-1)==true)
                                                                        {
                                                                            checkComputerWon();
                                                                        }
                                                                        else
                                                                        {
                                                                            if(blockDiagonal1(n-1)==true)
                                                                            {
                                                                                checkComputerWon();
                                                                            }
                                                                            else
                                                                            {
                                                                                if(blockDiagonal2(n-1)==true)
                                                                                {
                                                                                    checkComputerWon();
                                                                                }
                                                                                else
                                                                                {
                                                                                    if(PlayHorizontal(n-2)==true)
                                                                                    {
                                                                                        checkComputerWon();
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        if(PlayVertical(n-2)==true)
                                                                                        {
                                                                                            checkComputerWon();
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            if(PlayDiagonally1(n-2)==true)
                                                                                            {
                                                                                                checkComputerWon();
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                if(PlayDiagonally2(n-2)==true)
                                                                                                {
                                                                                                    checkComputerWon();
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    int x = (int)(Math.random()*(n));
                                                                                                    int y = (int)(Math.random()*(n));
                                                                                                    if((x<0 || x>=n) || (y<0 || y>=n))
                                                                                                    {
                                                                                                        while((x<0 || x>=n) || (y<0 || y>=n))
                                                                                                        {
                                                                                                            System.out.println("Here");
                                                                                                            x = (int)(Math.random()*(n));
                                                                                                            y = (int)(Math.random()*(n));
                                                                                                            System.out.println(x);
                                                                                                            System.out.println(y);
                                                                                                        }
                                                                                                    }
                                                                                                    else
                                                                                                    {
                                                                                                        if(b[x][y].getText().equals(c.sign) || b[x][y].getText().equals(p.sign))
                                                                                                        {
                                                                                                            while(b[x][y].getText().equals(c.sign) ||  b[x][y].getText().equals(p.sign))
                                                                                                            {
                                                                                                                x = (int)(Math.random()*(n));
                                                                                                                y = (int)(Math.random()*(n));
                                                                                                            }
                                                                                                        }
                                                                                                            b[x][y].setText(c.sign);
                                                                                                            EmptyCells--;
                                                                                                            PlayerTurn=true;
                                                                                                            checkComputerWon();
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
        }

    }
    void checkComputerWon()
    {
        if (checkHorizontal(c.sign) == 1 || checkVertical(c.sign) == 1 || checkDiagonal1(c.sign) == 1 || checkDiagonal2(c.sign) == 1)
        {
            somebodyOne=true;
            JOptionPane.showMessageDialog(this,"Computer Won !","Message",JOptionPane.PLAIN_MESSAGE);
        }
    }
    int checkHorizontal(String P)
    {
        int count = 0;
        int win = 0;
        for (int i = 0; i < n; i++)      //loop to check horizontally
        {
            for (int j = 0; j < n; j++)
            {
                if (b[i][j].getText() == P)
                {
                    count++;
                }
            }
            if (count == n)
            {
                win = 1;
                return win;
            }
            else
            {
                count = 0;
            }
        }

        return win;
    }
    int checkVertical(String P)
    {
        int win = 0;
        int count = 0;
        for (int i = 0; i < n; i++)      //loop to check vertically
        {
            for (int j = 0; j < n; j++)
            {
                if (b[j][i].getText() == P)
                {
                    count++;
                }
            }
            if (count == n)
            {
                win = 1;
                return win;
            }
            else
            {
                count = 0;
            }
        }
        return win;
    }
    int checkDiagonal1(String P)
    {
        int win = 0;
        int count = 0;
        for (int j=0,i = 0; i < n && j<n; i++,j++)
        {
            if(b[i][j].getText() == P)
            {
                count++;
            }
        }
        if (count == n)
        {
            win = 1;
        }
        else
        {
            win = 0;
        }
        return win;
    }
    int checkDiagonal2(String P)
    {
        int win = 0;
        int count = 0;
        int i, j;
        for (i = 0, j = n - 1; i < n && j >= 0; i++, j--)
        {
            if (b[i][j].getText() == P)
            {
                count++;
            }
        }
        if (count == n)
        {
            win = 1;
        }
        else
        {
            win = 0;
        }
        return win;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton b=(JButton)actionEvent.getSource();
        if(EmptyCells!=0)
        {
            if(b.getText().equals("-"))
            {
                if(PlayerTurn==true)
                {
                    b.setText(p.sign);
                    EmptyCells--;
                    if (checkHorizontal(p.sign) == 1 || checkVertical(p.sign) == 1 || checkDiagonal1(p.sign) == 1 || checkDiagonal2(p.sign) == 1)
                    {
                        somebodyOne=true;
                        JOptionPane.showMessageDialog(this,"Congratulations !! You Won !","Message",JOptionPane.PLAIN_MESSAGE);
                    }
                    PlayerTurn=false;
                    if(EmptyCells!=0)
                    {
                        Computerplay();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"It's a Draw!","Message",JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this,"Already Marked !!");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this,"It's a Draw!","Message",JOptionPane.PLAIN_MESSAGE);
        }


    }
}
public class Main {

    public static void main(String[] args) {
        GUI obj=new GUI();
    }
}

