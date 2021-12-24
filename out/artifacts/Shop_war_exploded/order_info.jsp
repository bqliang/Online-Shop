<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
</style>
</head>

<body>
	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>
	
	<div class="container">
		<div class="row">
			<div style="margin: 0 auto; margin-top: 10px; width: 950px;">
				<strong>订单详情</strong>
				<table class="table table-bordered">
					<tbody>
						<tr class="warning">
							<th colspan="5">订单编号:${order.oid}</th>
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

		</div>

		<div>
			<hr />
			<form class="form-horizontal" action="QueDingOrderServlet"
				style="margin-top: 5px; margin-left: 250px;">
				<input type="hidden" name="oid" value="${order.oid}">
				<div class="form-group">
					<label for="username" class="col-sm-1 control-label">地&nbsp;&nbsp;&nbsp;址</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" name="address"
							placeholder="请输入收货地址" value="${order.address}">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-1 control-label">收货人</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" name="name"
							placeholder="请输收货人" value="${order.name}">
					</div>
				</div>
				<div class="form-group">
					<label for="confirmpwd" class="col-sm-1 control-label">电&nbsp;&nbsp;&nbsp;话</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" name="telephone"
							placeholder="请输入联系方式" value="${order.telephone}">
					</div>
				</div>
				<input type="submit" value="确定订单" style="margin-left:150px">
			    &nbsp;&nbsp;&nbsp;<a href="DeleteOrderServlet?oid=${order.oid}">删除订单</a>
			    
			    &nbsp;&nbsp;&nbsp;<input class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						
			
			</form>

			<hr />

			
		</div>

	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>

</html>