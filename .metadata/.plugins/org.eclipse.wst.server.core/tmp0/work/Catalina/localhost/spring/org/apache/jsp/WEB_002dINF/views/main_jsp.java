/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.79
 * Generated at: 2022-07-28 03:44:03 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar", Long.valueOf(1658124978000L));
    _jspx_dependants.put("jar:file:/C:/spring-workspace/springProject/src/main/webapp/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/c.tld", Long.valueOf(1425946270000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("    \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>지켜보고 있다</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common/header.jsp", out, false);
      out.write("\r\n");
      out.write("	\r\n");
      out.write("	<div class=\"content\">\r\n");
      out.write("		<br><br>\r\n");
      out.write("		\r\n");
      out.write("		<div class=\"innerOuter\">\r\n");
      out.write("		\r\n");
      out.write("			<h4>게시글 Top 5</h4>\r\n");
      out.write("			<br>\r\n");
      out.write("			<a href=\"list.bo\" class=\"table table-hover\" align=\"center\">더보기</a>\r\n");
      out.write("			<br>\r\n");
      out.write("			\r\n");
      out.write("			<table id=\"boardList\" class=\"table table-hover\" align=\"center\">\r\n");
      out.write("			<thead>\r\n");
      out.write("				<tr>\r\n");
      out.write("					<th>글번호</th>\r\n");
      out.write("					<th>제목</th>\r\n");
      out.write("					<th>작성자</th>\r\n");
      out.write("					<th>조회수</th>\r\n");
      out.write("					<th>작성일</th>\r\n");
      out.write("					<th>첨부파일</th>\r\n");
      out.write("				</tr>\r\n");
      out.write("			</thead>\r\n");
      out.write("			\r\n");
      out.write("			\r\n");
      out.write("			<tbody>\r\n");
      out.write("				<!-- 현재 조회수가 가장 높은 상위 5개의 게시글 조회해서 뿌리기(ajax 이용해서)-->\r\n");
      out.write("			</tbody>\r\n");
      out.write("			</table>\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("\r\n");
      out.write("	<script>\r\n");
      out.write("		$(function(){\r\n");
      out.write("			topBoardList();\r\n");
      out.write("\r\n");
      out.write("			/*\r\n");
      out.write("			$('#boardList>tbody>tr').click(function(){\r\n");
      out.write("				location.href = 'detail.bo?bno=' + $(this).children.eq(0).text();\r\n");
      out.write("			})\r\n");
      out.write("			*/\r\n");
      out.write("			// 동적으로 만들어진 요소에 이벤트 부여방법\r\n");
      out.write("			$(document).on('click', '#boardList>tbody>tr', function(){\r\n");
      out.write("				location.href = 'detail.bo?bno=' + $(this).children().eq(0).text();\r\n");
      out.write("			})\r\n");
      out.write("\r\n");
      out.write("			setInterval(topBoardList, 1000);	// 1000이 1초임\r\n");
      out.write("		})\r\n");
      out.write("		function topBoardList(){\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : 'topList.bo',\r\n");
      out.write("				success : function(data){\r\n");
      out.write("					//console.log(data);\r\n");
      out.write("					\r\n");
      out.write("					let value = '';\r\n");
      out.write("					for(let i in data){\r\n");
      out.write("						value += '<tr>'\r\n");
      out.write("							   + '<td>' + data[i].boardNo + '</td>'\r\n");
      out.write("							   + '<td>' + data[i].boardTitle + '</td>'\r\n");
      out.write("							   + '<td>' + data[i].boardWriter + '</td>'\r\n");
      out.write("							   + '<td>' + data[i].count + '</td>'\r\n");
      out.write("							   + '<td>' + data[i].createDate + '</td>'\r\n");
      out.write("							   + '<th>';\r\n");
      out.write("							   	if(data[i].originName != null){\r\n");
      out.write("							   		value += '★';\r\n");
      out.write("							   	}\r\n");
      out.write("							   + '</th></tr>'\r\n");
      out.write("					}\r\n");
      out.write("					$('#boardList>tbody').html(value);\r\n");
      out.write("				}, error : function(){\r\n");
      out.write("					console.log('top5 ajax실패여');\r\n");
      out.write("	\r\n");
      out.write("				}\r\n");
      out.write("			})\r\n");
      out.write("		}\r\n");
      out.write("	</script>\r\n");
      out.write("\r\n");
      out.write("	\r\n");
      out.write("	");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common/footer.jsp", out, false);
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
