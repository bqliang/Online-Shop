<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	     <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
          <script src="js/bootstrap.min.js" type="text/javascript"></script>
	</HEAD>
	
	<body>
		<!--  -->
		<div style="width:95%;margin:0 auto;text-align:center;" >
			
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>订单信息</STRONG>
						</strong>
					</td>
				</tr>
				<tr>
				   <table class="table table-bordered">
					<tbody>
						<tr class="warning">
							<th colspan="3">订单编号:${order.oid}</th>
							<th colspan="2">订单状态:
							  <c:if test="${order.state==1}">已付款</c:if>
						      <c:if test="${order.state==2}">已发货</c:if>
						      <c:if test="${order.state==3}">已收货</c:if>
						      <c:if test="${order.state==4}">已评价</c:if>
							
							</th>
						</tr>
						<tr class="warning">
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<c:forEach items="${order.orderItems}" var="orderItem">
						<tr class="active">
							<td width="60" width="40%"><input type="hidden" name="id"
								value="22"> <img src="${orderItem.product.pimage }" width="70"
								height="60"></td>
							<td width="30%"><a target="_blank"> ${orderItem.product.pname }</a></td>
							<td width="20%">￥${orderItem.product.shop_price }</td>
							<td width="10%">${orderItem.count }</td>
							<td width="15%"><span class="subtotal">￥${orderItem.subtotal }</span></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div style="text-align: right; margin-right: 120px;">
				商品金额: <strong style="color: #ff6600;">￥${order.total}</strong>
			</div>	
			<hr />
			<div style="width:100%;text-align:center">
			    <div><label >地&nbsp;&nbsp;&nbsp;址</label>
						<input type="text"  disabled="disabled"
							placeholder="请输入收货地址" value="${order.address}">
					</div>
					
					<div ><label >收货人</label>
						<input type="text"  disabled="disabled"
							placeholder="请输收货人" value="${order.name}">
					</div>
		
					<div ><label  >电&nbsp;&nbsp;&nbsp;话</label>
						<input type="text"  disabled="disabled"
							value="${order.telephone}">
					</div>
					
					<div>
					<label>评价</label>
					   <div>
						<textarea cols="30" rows="10" disabled="disabled">${order.assess}</textarea>
					  </div>
					   <INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
				    </div>
			
			
			
			</div>
				
				
				</tr>
			</table>
		</div>
	</body>
</HTML>