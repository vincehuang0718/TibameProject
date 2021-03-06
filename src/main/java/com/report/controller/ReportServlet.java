package com.report.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmt.model.CmtService;
import com.cmt.model.CmtVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.movie.model.MovieService;
import com.movie.model.MovieVO;
import com.report.model.ReportService;
import com.report.model.ReportVO;

@WebServlet("/ReportServlet.do")
public class ReportServlet extends HttpServlet {
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			
			Integer mvId = Integer.valueOf(req.getParameter("mvId"));
			Integer cmId = Integer.valueOf(req.getParameter("cmId"));
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			String rpText = req.getParameter("rpText");
			String rpType = req.getParameter("rpType");
			Integer rpState = Integer.valueOf(req.getParameter("rpState"));
			Timestamp rpDate = new Timestamp(System.currentTimeMillis());
//			Timestamp rpDate = java.sql.Timestamp.valueOf(req.getParameter("rpDate"));
			/* **************************************************************** */
			MovieService movieSvc = new MovieService();
			MovieVO movieVO = movieSvc.findByPrimaryKey(mvId);
			
			
			ReportVO reportVO = new ReportVO();
			reportVO.setCmId(cmId);
			reportVO.setMemberId(memberId);
			reportVO.setRpText(rpText);
			reportVO.setRpType(rpType);
			reportVO.setRpState(rpState);
			reportVO.setRpDate(rpDate);
			
			ReportService rpSvc = new ReportService();
			reportVO = rpSvc.insert(cmId, memberId, rpText, rpType, rpState, rpDate);
			
			String url = "/front_end/movieDetail/movie_detail.jsp";
			req.setAttribute("movieVO", movieVO);
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			
		}
		if("getOne_For_Update".equals(action)) {
			
			Integer rpId = Integer.valueOf(req.getParameter("rpId"));
			Integer cmId = Integer.valueOf(req.getParameter("cmId"));
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			// ??????????????????????????????
			ReportService rpSvc = new ReportService();
			ReportVO reportVO = rpSvc.findByPrimaryKey(rpId);
			// ??????????????????VO & ????????????????????????ID????????????????????????
			CmtService cmtSvc = new CmtService();
			CmtVO cmtVO = cmtSvc.getOneCmt(cmId);
			Integer cmtMemberId = cmtVO.getMEMBER_ID();
			
			// ?????????????????????
			MemberService mbSvc = new MemberService();
			MemberVO rpMemberVO = mbSvc.getOneMem(memberId);
			MemberVO cmtMemberVO = mbSvc.getOneMem(cmtMemberId);
			
			req.setAttribute("reportVO", reportVO);
			req.setAttribute("cmtVO", cmtVO);
			req.setAttribute("rpMemberVO", rpMemberVO);
			req.setAttribute("cmtMemberVO", cmtMemberVO);
			String url = "/back_end/ManageReport/editReport.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			
		}
		if("update".equals(action)) {
			
			Integer rpId = Integer.valueOf(req.getParameter("rpId"));
			Integer cmId = Integer.valueOf(req.getParameter("cmId"));
			Integer changeCmState = Integer.valueOf(req.getParameter("changeCmState"));
			
			// ????????????????????????,?????????????????????????????????
			Integer rpState = 1;
			/* ***************************????????????????????????********************************* */
			CmtService cmtSvc = new CmtService();
			cmtSvc.updateCmtState(cmId, changeCmState);
			/* ***************************??????????????????************************************* */
			ReportService rpSvc = new ReportService();
			// ????????????
			if(req.getParameter("updateSameRP")!=null) {
				rpSvc.updateSameRP(cmId);
			}else {
				rpSvc.update(rpId, rpState);
			}
			
			String url = "/back_end/ManageReport/manageReport.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

}
