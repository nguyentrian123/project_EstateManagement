<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<c:url var="customerAPI" value="/api/customer" />
<c:url var="customerList" value="/admin/customerlist" />
<c:url var="assignStaff" value="/api/customer/assign" />
<c:url var="addURL" value="/admin/customeredit" />

<!DOCTYPE html>
<html>
<head>
<title>Quản lý khách hàng</title>
</head>
<body>

	<div class="main-content">
		<div class="main-content-inner">

			<form:form modelAttribute="modelSearch" id="formSubmit" method="get">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">

							<c:if test="${not empty message}">
								<div class="alert alert-${alert}">${message}</div>
							</c:if>


							<div class="widget-box">
								<div class="widget-header">
									<h4 class="widget-title">Tìm Kiếm</h4>

									<div class="widget-toolbar">
										<a href="#" data-action="collapse"> <i
											class="ace-icon fa fa-chevron-up"></i>
										</a>
									</div>

									<div class="widget-body">
										<div class="widget-main">
											<div class="form-horizontal">

												<div class="form-group">
													<div class="col-sm-4">
														<div>
															<label for="name" style="color: black;">Tên khách
																hàng</label>
															<form:input path="fullName" class="form-control" />
														</div>
													</div>

													<div class="col-sm-4">
														<div>
															<label for="phone" style="color: black;">Di động
															</label>
															<form:input path="phone" type="number"
																class="form-control" />
														</div>
													</div>

													<div class="col-sm-4">
														<div>
															<label for="email" style="color: black;">Email </label>
															<form:input path="email" class="form-control" />
														</div>
													</div>

												</div>


												<div class="form-group">
													<security:authorize access="hasRole('MANAGER')">
														<div class="col-sm-2">
															<div>
																<label for="name" style="color: black;">Nhân
																	viên phụ trách</label>
																<form:select path="staff" class="form-control">
																	<form:option value=""
																		label="---Chọn nhân viên quản lý---" />
																	<form:options items="${staffmaps}" />
																</form:select>
															</div>
														</div>
													</security:authorize>

													<div class="col-sm-2 pull-right">
														<button id="searchCustomer" type="submit"
															class="btn btn-success btn-md dropdown-toggle">
															Tìm kiếm <i
																class="ace-icon fa fa-arrow-right icon-on-right"></i>
														</button>
													</div>

												</div>


											</div>


										</div>
									</div>


								</div>


							</div>
							<!-- /.end  -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->

					<!--hết-->
					<div class="row" style="margin-right: 0px;">
						<div class="pull-right">
							<a href="${addURL}">
								<button class="btn btn-white btn-info btn-bold"
									data-toggle="tooltip" type="button" title="Thêm khách hàng">
									<i class="fa fa-plus-circle" aria-hidden="true"></i>

								</button>
							</a>

							<security:authorize access="hasRole('MANAGER')">
								<button id="btnDelete" type="button" disabled
									class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
									data-toggle="tooltip" title="Xóa bài viết"
									onclick="showAlertBeforeDelete()">
									<span> <i class="fa fa-trash-o bigger-110 pink"></i>
									</span>
								</button>
							</security:authorize>
							<security:authorize access="!hasRole('MANAGER')">
								<button type="button" disabled
									class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
									data-toggle="tooltip" title="Xóa bài viết"
									onclick="showAlertBeforeDelete()">
									<span> <i class="fa fa-trash-o bigger-110 pink"></i>
									</span>
								</button>
							</security:authorize>

						</div>
					</div>
					</br>



					<div class="row">
						<div class="col-xs-12">

							<div>
								<table id="customerList"
									class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center"><label class="pos-rel"> <input
													type="checkbox" class="ace" /> <span class="lbl"></span>
											</label></th>
											<th>Tên</th>
											<th>Nhân viên quản lý</th>
											<th>Di động</th>
											<th>Email</th>
											<th>Nhu cầu</th>
											<th>Người nhập</th>
											<th>Ngày nhập</th>
											<th>Thao tác</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach var="item" items="${model.data}">

											<tr>
												<td class="center"><label class="pos-rel"> <input
														type="checkbox" class="ace" value="${item.id}"
														id="checkbox_${item.id}" /> <span class="lbl"></span>
												</label></td>

												<td>${item.fullName}</td>
												<td>${item.fullNameStaff}</td>
												<td>${item.phone}</td>
												<td>${item.email}</td>
												<td>${item.need}</td>
												<td>${item.createdBy}</td>								
												<td>${item.createdDate}</td>
												<td>
													<div class="hidden-sm hidden-xs action-buttons">

														<security:authorize access="hasRole('MANAGER')">
															<button class="btn btn-xs btn-info" data-toggle="tooltip"
																type="button" title="Giao khách hàng"
																
																onclick="assignmentCustomer(${item.id})">
																<i class="fa fa-bars"></i>
															</button>
														</security:authorize>
														<security:authorize access="!hasRole('MANAGER')">
															<button class="btn btn-xs btn-info" data-toggle="tooltip"
																type="button" title="Giao khách hàng" disabled>
																<i class="fa fa-bars"></i>
															</button>
														</security:authorize>


														<c:url var="updateCustomerURL" value="/admin/customeredit">
															<c:param name="id" value="${item.id}" />
														</c:url>
														<a href='${updateCustomerURL}'>
															<button class="btn btn-xs btn-info" type="button"
																data-toggle="tooltip" title="Sửa khách hàng">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</a>


														<c:url var="detailCustomerURL"
															value="/admin/customerdetail">
															<c:param name="id" value="${item.id}" />
														</c:url>
														<a href='${detailCustomerURL}'>
															<button class="btn btn-xs btn-info" type="button"
																data-toggle="tooltip" title="Chi tiết khách hàng">
																<i
																	class="normal-icon ace-icon fa fa-eye white bigger-130"></i>
															</button>
														</a>

													</div>

													<div class="hidden-md hidden-lg">
														<div class="inline pos-rel">
															<button class="btn btn-minier btn-yellow dropdown-toggle"
																data-toggle="dropdown" data-position="auto">
																<i
																	class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
															</button>

															<ul
																class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
																<li><a href="#" class="tooltip-info"
																	data-rel="tooltip" title="View"> <span class="blue">
																			<i class="ace-icon fa fa-search-plus bigger-120"></i>
																	</span>
																</a></li>

																<li><a href="#" class="tooltip-success"
																	data-rel="tooltip" title="Edit"> <span
																		class="green"> <i
																			class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																	</span>
																</a></li>


															</ul>
														</div>
													</div>
												</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
								
								<ul class="pagination" id="pagination"></ul>
								<form:hidden path="page"  value="1" />
								
							</div>
						</div>
					</div>




				</div>
				<!-- /.page-content -->
			</form:form>

		</div>
	</div>
	<!-- /.main-content -->


	<div id="assignmentCustomerModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Danh sách nhân viên</h4>
				</div>
				<div class="modal-body">
					<table class="table table-condensed" id="staffList">
						<thead>
							<tr>
								<th style="text-align: center;">Chọn nhân viên</th>
								<th style="text-align: center;">Tên nhân viên</th>

							</tr>
						</thead>
						<tbody>
							<!--  -->
						</tbody>
					</table>
					<input type="hidden" id="customerId" name="customerId" value="" />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						id="btnAssignCustomer">Giao khách hàng</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
				</div>
			</div>

		</div>
	</div>

	<script>
	
	var totalPages = ${model.totalPages};
	var currentPage = ${model.page}; 
	$(function() {
		window.pagObj = $('#pagination').twbsPagination({
			totalPages :  totalPages ,
			visiblePages : 5,
			startPage : currentPage,
			onPageClick : function(event,page) {   
				 if (currentPage != page) {
					$('#page').val(page);
					$('#formSubmit').submit();
				} 
				
			}
		});
	})
	
			  function assignmentCustomer(customerId)
			  {
					openModalAssginmentCustomer();
					loadStaff(customerId);
					$('#customerId').val(customerId);
			  }
			  
			  function loadStaff(customerId) {
				  $.ajax({
					 type: "GET",
					 url: "${customerAPI}/staffs/?customerid="+customerId+" ",
					 dataType: "json",
					  success : function(response) {
						  var row = '';
						  $.each(response.data, function (index,item) {
							  row += '<tr>';
							  row += '<td class="text-center"><input type="checkbox" value='+item.id+'  id="checkbox_'+item.id+'" class="check-box-element" '+item.checked+' /></td>';
							  row += '<td class="text-center">'+item.fullName+' </td>';
							  row += '</tr>';
                          });

						  $('#staffList tbody').html(row); // add html vào cái nơi mà mình chỉ định
                      },
                      error : function(response) {
                          console.log("fail");
                          console.log(response);
                      }

				  });
              }

			  function openModalAssginmentCustomer(){
					$('#assignmentCustomerModal').modal();
			  }
			 
			  $('#btnAssignCustomer').click(function (e){
				  e.preventDefault();
				  var data = {};
				  data['customerId'] = $('#customerId').val();
				  
				  var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
						return $(this).val(); 
	              }).get(); 
	
				  data['staffs'] = staffs;	
				  assignStaff(data);
			  });
			  
			  function assignStaff(data) {
                  $.ajax({
                      url : '${assignStaff}',
                      type : 'POST',
                      contentType : 'application/json',
                      data : JSON.stringify(data),
                      dataType : 'json',
                      success : function(response) {             
                          window.location.href = "${customerList}";
                      },
                      error : function(response) {
                          console.log("failed");
                          console.log(response);
                      }
                  });
              }
			  
			  
			  function showAlertBeforeDelete(callback) {
                  swal({
                      title: "Xác nhận xóa",
                      text: "Bạn có chắc chắn xóa những dòng đã chọn",
                      type: "warning",
                      showCancelButton: true,
                      confirmButtonText: "Xác nhận",
                      cancelButtonText: "Hủy bỏ",
                      confirmButtonClass: "btn btn-success",
                      cancelButtonClass: "btn btn-danger"
                  }).then(function (res) {
                      if(res.value){
                    	 	event.preventDefault();
                    		var ids = $('#customerList').find(' tbody input[type=checkbox]:checked').map(function () {
          			            return $(this).val();
          			        }).get();
                    		deleteCustomer(ids);
          					
                      }else if(res.dismiss == 'cancel'){
                          console.log('cancel');
                      }
                  });
              }
      

      		
      		function deleteCustomer(data) {
      	        $.ajax({
      	            url: '${customerAPI}',
      	            type: 'DELETE',
      	         	dataType: 'json',
      	            contentType: 'application/json',
      	            data: JSON.stringify(data),
      	            success: function (result) {
      	                window.location.href = "${customerList}?message=delete_success";
      	            },
      	            error: function (error) {
      	            	window.location.href = "${customerList}?message=error_system";
      	            }
      	        });
      	    }
			  
		  </script>

</body>
</html>