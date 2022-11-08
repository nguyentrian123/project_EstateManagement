<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chi tiết khách hàng</title>
</head>
<body>

	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
					</li>

					<li><a href="#">Forms</a></li>
					<li class="active">Form Elements</li>
				</ul>
				<!-- /.breadcrumb -->

			</div>

			<div class="page-content">
				<form:form modelAttribute="model" role="form" id="formSubmit"
					method="get" class="form-horizontal">

					<div class="page-header">
						<h1>Thông tin khách hàng</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">


							<c:if test="${not empty message}">
								<div class="alert alert-${alert}">${message}</div>
							</c:if>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-left "
									for="form-field-1"> Tên đầy đủ </label>
								<div class="col-sm-9">
									<form:input path="fullName" class="form-control" disabled="true" />
								</div>
							</div>



							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-left"
									for="form-field-1"> Số điện thoại </label>
								<div class="col-sm-9">
									<form:input path="phone" class="form-control"  disabled="true"/>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-left"
									for="form-field-1"> Email </label>
								<div class="col-sm-9">
									<form:input path="email" class="form-control " disabled="true"/>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-left"
									for="form-field-1"> Tên công ty </label>
								<div class="col-sm-9">
									<form:input path="companyName" class="form-control" disabled="true"/>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-left"
									for="form-field-1"> Nhu cầu </label>
								<div class="col-sm-9">
									<form:input path="need" class="form-control" disabled="true"/>
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-left"
									for="form-field-1"> Ghi chú </label>
								<div class="col-sm-9">
									<form:textarea path="note" class="form-control" rows="3" disabled="true"></form:textarea>
								</div>
							</div>

							


						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->



					<c:forEach var="item" items="${transactiontypemaps}">
						
							<div class="page-header">
								<c:if test="${not empty param.id }">
								<h1>${item.value}</h1>

								</c:if>
								
								<c:if test="${empty param.id }">
									<h1>${item.value}</h1>
								</c:if>	
							</div>
							
							<div class="row" >
									<div class="col-xs-12">
	
										<div>
											<table id="transactionList" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>Ngày</th>
														<th>Ghi chú</th>
															
													</tr>
												</thead>
												
												
												<tbody>
													 <c:forEach var="test" items="${transactions}"> 
													 	<c:if test="${item.key == test.type}">
													 		<tr>
																<td>${test.createdDate}</td>
																<td>${test.note}</td>
															</tr>
													 	</c:if>
														
													 </c:forEach> 
												</tbody> 
												
												
											</table>
	
										</div>
									</div>
							</div>
							
						</c:forEach>

				</form:form>
			</div>
			<!-- /.page-content -->
		</div>
	</div>
	<!-- /.main-content -->
	
	
	
</body>
</html>