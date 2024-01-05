package board;

import java.util.Date;

// "NUM", "TITLE", "WRITER", "CONTENT", "REGDATE", "CNT"
public class BoardDto {
	private int num; // 게시글 번호
	private String title; // 제목
	private String writer; // 작성자
	private String content; // 내용
	private Date regdate; // 작성일
	private int cnt; // 조회수

	public BoardDto() {}
	
	public BoardDto(int num, String title, String writer, String content, Date regdate, int cnt) {
		super();
		this.num = num;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.cnt = cnt;
	}
	
	public int getNum() { return num; }
	
	public void setNum(int num) { this.num = num; }
	
	public String getTitle() { return title; }
	
	public void setTitle(String title) { this.title = title; }
	
	public String getWriter() { return writer; }
	
	public void setWriter(String writer) { this.writer = writer; }
	
	public String getContent() { return content; }
	
	public void setContent(String content) { this.content = content; }
	
	public Date getRegdate() { return regdate; }
	
	public void setRegdate(Date regdate) { this.regdate = regdate; }
	
	public int getCnt() { return cnt; }
	
	public void setCnt(int cnt) { this.cnt = cnt; }
	
	@Override
	public String toString() {
		return "BoardDto [num=" + num + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", regdate=" + regdate + ", cnt=" + cnt + "]";
	}
	
}