package com.sharBoard.board.service;

public class BoardVO {

    private String board_id;
    private String board_title;
    private String board_writer;
    private String board_crd_date;
    private String board_hit;

    public String getBoard_id() {
        return board_id;
    }

    public void setBoard_id(String board_id) {
        this.board_id = board_id;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_writer() {
        return board_writer;
    }

    public void setBoard_writer(String board_writer) {
        this.board_writer = board_writer;
    }

    public String getBoard_crd_date() {
        return board_crd_date;
    }

    public void setBoard_crd_date(String board_crd_date) {
        this.board_crd_date = board_crd_date;
    }

    public String getBoard_hit() {
        return board_hit;
    }

    public void setBoard_hit(String board_hit) {
        this.board_hit = board_hit;
    }
}
