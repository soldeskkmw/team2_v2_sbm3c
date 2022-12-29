package dev.mvc.notice;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.mvc.tool.Tool;

// import dev.mvc.contents.ContentsVO;

@Component("dev.mvc.notice.NoticeProc")
public class NoticeProc implements NoticeProcInter {
  // NoticeDAOInter interface 만 존재하고 구현 class는 존재하지 않음.
  // interface는 객체를 만들 수 없고 할당만 받을 수 있음.
  
  @Autowired
  private NoticeDAOInter noticeDAO;
  
  public NoticeProc() {
    // System.out.println("-> NoticeProc created.");
    // System.out.println("-> NoticeProc: " + (NoticeDAO == null));
  }
  
  @Override
  public int notice_create(NoticeVO noticeVO) {
    int noticecnt = this.noticeDAO.notice_create(noticeVO); // MyBATIS가 처리한 레코드 갯수가 return됨
    
    // System.out.println("-> NoticeProc create: " + (NoticeDAO == null));
    return noticecnt;
  }

  @Override
  public ArrayList<NoticeVO> notice_list() {
    ArrayList<NoticeVO> list = this.noticeDAO.notice_list();

    return list;
  }

  
  @Override
  public NoticeVO notice_read(int noticeno) {
    NoticeVO noticeVO = this.noticeDAO.notice_read(noticeno);
    
    String noticecontent = noticeVO.getNoticecontent();
    
    noticecontent = Tool.convertChar(noticecontent);
    noticeVO.setNoticecontent(noticecontent);
    
    return noticeVO;
  }

  @Override
  public int notice_read_update(NoticeVO noticeVO) {
    int noticecnt = this.noticeDAO.notice_read_update(noticeVO);
    
    String noticecontent = noticeVO.getNoticecontent();
    
    noticecontent = Tool.convertChar(noticecontent);
    noticeVO.setNoticecontent(noticecontent);
    
    return noticecnt;
  }

  @Override
  public int notice_read_delete(int noticeno) {
    int noticecnt = this.noticeDAO.notice_read_delete(noticeno);
    return noticecnt;
  }

}

