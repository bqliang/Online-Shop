<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/admin/product/add.jsp";
			}
		</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1" style="width:95%;margin:0 auto;"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table class="table table-bordered">
					<c:forEach items="${orderList}" var="order">
					<tbody style="text-align:center;">
						<tr class="success">
							<th colspan="5">订单编号:${order.oid}</th>
						</tr>
						<tr class="warning">
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<c:forEach items="${order.orderItems}" var="orderItem" >
						<tr class="active" >
							<td width="60" width="40%"><input type="hidden" name="id"
								value="22"> <img src="${orderItem.product.pimage}" width="70"
								height="60"></td>
							<td width="30%"><a target="_blank">${orderItem.product.pname}</a></td>
							<td width="15%">￥${orderItem.product.shop_price}</td>
							<td width="15%">${orderItem.count}</td>
							<td width="15%"><span class="subtotal">￥${orderItem.subtotal}</span></td>
						</tr>
						</c:forEach>
						<tr>
						  <td>合计:&nbsp;&nbsp;<font style="color:red">￥${order.total}</font></td>
						  <td>${order.orderTime}</td>
						  <td>
						     <c:if test="${order.state==1}">
						       <a href="ManageOrderServlet?state=1&oid=${order.oid}&admin=admin" style="color:red">发货</a>
						       </c:if></td>
						  <td>
						      <c:if test="${order.state==1}">已付款</c:if>
						      <c:if test="${order.state==2}">已发货</c:if>
						      <c:if test="${order.state==3}">已收货</c:if>
						      <c:if test="${order.state==4}">已评价</c:if>
						   </td>
						  <td><a href="OrderInfoServlet?oid=${order.oid}" style="color:blue">查看详情</a></td>
						  </td>
						</tr>
					</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
		<!--分页 -->
	<div style="width: 380px; margin: 0 auto; margin-top: 5px;">
		<ul class="pagination" style="text-align: center; margin-top: 5px;">
			<c:if test="${currentPage==1}">
			  <li class="disabled"><a href="#" aria-label="Previous"><span
					aria-hidden="true">&laquo;</span></a></li>
		    </c:if>
		    <c:if test="${currentPage!=1}">
			  <li><a href="AllOrderListServlet?currentPage=${currentPage-1}" aria-label="Previous"><span
					aria-hidden="true">&laquo;</span></a></li>
		    </c:if>
			
			
			<c:forEach begin="1" end="${totalPage}" var="pageNum">
			   <c:if test="${currentPage==pageNum}">
			      <li class="active"><a href="AllOrderListServlet?currentPage=${pageNum}">${pageNum }</a></li>
			   </c:if>
			   <c:if test="${currentPage!=pageNum }">
			      <li><a href="AllOrderListServlet?currentPage=${pageNum}">${pageNum}</a></li>
			   </c:if>
			</c:forEach>
			
			<c:if test="${currentPage!=totalPage}">
			    <li><a href="AllOrderListServlet?currentPage=${currentPage+1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			         </a>
			    </li>
			</c:if>
		
		
			 <c:if test="${currentPage==totalPage}">
			    <li class="disabled"><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			         </a>
			    </li>
			</c:if>
		</ul>
	</div>
	</form>
</body>
</HTML>

