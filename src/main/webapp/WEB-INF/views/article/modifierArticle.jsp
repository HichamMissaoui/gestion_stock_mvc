<%@ include file="/WEB-INF/views/includes/includes.jsp"%>
<html lang="fr">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Bootstrap Admin Theme</title>

<!-- Bootstrap Core CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/resources/dist/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- DataTables CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/datatables-plugins/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link
	href="<%=request.getContextPath()%>/resources/vendor/datatables-responsive/dataTables.responsive.css"
	rel="stylesheet">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<%@ include file="/WEB-INF/views/menu_top/topMenu.jsp"%>

			<%@ include file="/WEB-INF/views/menu_left/leftMenu.jsp"%>

			<!-- /.navbar-static-side -->
		</nav>

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							<fmt:message key="article.nouveau" />
						</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<fmt:message key="article.nouveau" />
							</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
							<c:url value="/article/enregistrer" var="urlEnregistrer" />
								<f:form modelAttribute="article" action="${urlEnregistrer }" method="post" enctype="multipart/form-data">
									<f:hidden path="id"/>
									<f:hidden path="photo"/>
									<div class="form-group">
										<label><fmt:message key="common.code" /></label> 
										<f:input path="code" class="form-control" placeholder="code" required="required" />
									</div>
									<div class="form-group">
										<label><fmt:message key="common.designation" /></label> 
										<f:input path="designation" class="form-control" placeholder="designation" required="required" />
									</div>
									<div class="form-group">
										<label><fmt:message key="common.prix.unitaireHT" /></label> 
										<f:input path="prixUnitaireHT" class="form-control" placeholder="Prix Unitaire HT" required="required" />
									</div>
									<div class="form-group">
										<label><fmt:message key="common.tva" /></label> 
										<f:input path="tauxTva" class="form-control" placeholder="Taux Tva" required="required" />
									</div>
									<div class="form-group">
										<label><fmt:message key="common.prix.unitaireTTC" /></label> 
										<f:input path="prixUnitaireTTC" class="form-control" placeholder="Prix Unitaire TTC" required="required" />
									</div>
									<div class="form-group">
										<label><fmt:message key="category" /></label>
										<f:select class="form-control" path="category.id" items="${categories }" itemLabel="designation" itemValue="id">
										</f:select>
									</div>
									<div class="form-group">
                                            <label><fmt:message key="common.photo" /></label>
                                            <input type="file" name="file">
                                        </div>
									<div class="panel-footer">
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-save">&nbsp;<fmt:message key="common.enregistrer" /></i>
										</button>
										<a href="<c:url value="/article/" />" class="btn btn-danger">
											<i class="fa fa-arrow-left">&nbsp;<fmt:message key="common.annuler" /></i>
										</a>

									</div>
								</f:form>

								<!-- /.table-responsive -->

							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
					<!-- /.col-lg-12 -->
				</div>
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/sb-admin-2.js"></script>

	<!-- DataTables JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/vendor/datatables/js/jquery.dataTables.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/vendor/datatables-responsive/dataTables.responsive.js"></script>

<!-- My Custom JavaScript files -->
	<script
		src="<%=request.getContextPath()%>/resources/js/article.js"></script>

	
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$('#dataTables-example').DataTable({
				responsive : true
			});
		});
	</script>
</body>

</html>
