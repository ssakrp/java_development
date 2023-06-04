package com.knyazev;
import java.util.Scanner;


class Node
{
    private int info;
    public Node left = null;
    public Node right = null;
    public Node(int new_info)
    {
        info = new_info;
    }
    public int getInfo()
    {
        return info;
    }
    public void setInfo(int new_info)
    {
        info = new_info;
    }
}

class Tree
{
    public Node root = null;
    public void output(Node start)
    {
        if (this.root == null)
        {
            System.out.println("Tree is empty!");
            return;
        }
        if (start.left != null)
            output(start.left);
        System.out.println(start.getInfo());
        if (start.right != null)
            output(start.right);
    }
    public Node add(Node start, int new_info) throws Exception
    {
        if (check(this.root, new_info))
            throw new Exception("The element is already in set");
        if (start == null)
        {
            return new Node(new_info);
        }
        else if (new_info < start.getInfo())
            start.left = add(start.left, new_info);
        else if (new_info > start.getInfo())
            start.right = add(start.right, new_info);
        return start;
    }
    public boolean check(Node start, int need_info)
    {
        if (start == null)
            return false;
        if (start.getInfo() == need_info)
            return true;
        if (need_info < start.getInfo())
            return check(start.left, need_info);
        else
            return check(start.right, need_info);
    }
    public Node min(Node start)
    {
        if (start.left == null)
            return start;
        return min(start.left);
    }
    public Node delete(Node start, int need_info)
    {
        if (start == null)
            return null;
        if (need_info < start.getInfo())
            start.left = delete(start.left, need_info);
        else if (need_info > start.getInfo())
            start.right = delete(start.right, need_info);
        else if (start.left != null && start.right != null)
        {
            start.setInfo(min(start.right).getInfo());
            start.right = delete(start.right, start.getInfo());
        }
        else
        {
            if (start.left != null)
                start = start.left;
            else if (start.right != null)
                start = start.right;
            else
                start = null;
        }
        return start;
    }
    public void union(Node another_tree_node)
    {
        if (another_tree_node == null)
        {
            System.out.println("Another tree is empty");
            return;
        }
        if (another_tree_node.left != null)
            union(another_tree_node.left);
        try
        {
            this.root = add(this.root, another_tree_node.getInfo());
        }
        catch (Exception a) {}
        if (another_tree_node.right != null)
            union(another_tree_node.right);
    }
}

public class Main
{

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Tree test = new Tree();
        System.out.println("Создали пустое дерево, выведем его");
        test.output(test.root);
        System.out.println("Заполним дерево");
        System.out.println("Введите кол-во элементов для включения");
        int k = in.nextInt();
        System.out.println("Вводите элементы");
        for (int i = 0; i < k; ++i)
        {
            int a = in.nextInt();
            try
            {
                test.root = test.add(test.root, a);
            }
            catch (Exception t) {}
        }
        System.out.println("Ваше дерево");
        test.output(test.root);
        System.out.println("Введите кол-во элементов для удаления");
        k = in.nextInt();
        System.out.println("Вводите элементы");
        for (int i = 0; i < k; ++i)
        {
            int a = in.nextInt();
            test.root = test.delete(test.root, a);
        }
        System.out.println("Ваше дерево");
        test.output(test.root);
        System.out.println("Протестируем объединение: введите кол-во элементов для второго дерева");
        Tree test2 = new Tree();
        k = in.nextInt();
        System.out.println("Вводите элементы");
        for (int i = 0; i < k; ++i)
        {
            int a = in.nextInt();
            try
            {
                test2.root = test2.add(test2.root, a);
            }
            catch (Exception t) {}
        }
        System.out.println("Ваше второе дерево");
        test2.output(test2.root);
        System.out.println("Объединеяем 1 со 2-ым");
        test.union(test2.root);
        System.out.println("Результат объединения");
        test.output(test.root);
        System.out.println("Программа протестирована");
    }
}