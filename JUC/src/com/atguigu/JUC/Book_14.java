package com.atguigu.JUC;



public class Book_14 {
    private int id;
    private String bookName;
    private double price;

//链式编程 + 流式计算
    public static void main(String[] args) {

        Book_14 book = new Book_14();

        book.setId(12);
    }








    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
