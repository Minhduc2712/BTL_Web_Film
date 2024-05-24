<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FilmViet - Danh Sách Tập</title>

<%@ include file="/views/admin/common/head.jsp"%>
</head>

<body>
	<!--  Body Wrapper -->
	<div class="page-wrapper" id="main-wrapper" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed">

		<!-- Sidebar Start -->
		<%@ include file="/views/admin/common/assied.jsp"%>
		<!--  Sidebar End -->

		<!--  Main wrapper -->
		<div class="body-wrapper">

			<!--  Header Start -->
			<%@ include file="/views/admin/common/header.jsp"%>
			<!--  Header End -->

			<div class="container-fluid">
				<div class="card">
					<div class="card-body">
						<a class="btn btn-primary float-end" href="episodeadd" role="button">
							<i class="ti ti-file-plus"></i> Phim mới
						</a>
						<h5 class="card-title fw-semibold mb-4 mt-2">Danh Sách Video
							Đang Công Chiếu</h5>
						<div class="card">
							<div class="card-body">
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th scope="col">#</th>
												<th scope="col">Tập</th>
												<th scope="col">Tên</th>
												<th scope="col">Hành động</th>
											</tr>
										</thead>
										<tbody>


											<c:forEach items="${episodes}" var="episode" varStatus="loop">
												<tr>
													<td scope="row">${episode.id}</td>

													<td>${episode.episodeNumber}</td>
													<td width="100px">${episode.title}</td>
													<td>
														<div class="btn-group" role="group">
															<button class="btn btn-primary ms-2 rounded-2"
																onclick="editEpisodeGetId('${episode.id}')">Sửa</button>
															<button class="btn btn-danger ms-2 rounded-2"
																onclick="deleteEpisode('${episode.id}')">Xoá</button>
															<button type="button" data-bs-toggle="modal"
																data-bs-target="#modalLiveDemo${loop.index}"
																class="btn btn-success ms-2 rounded-2">Xem</button>
														</div>
													</td>
												</tr>

												<!-- Modal -->
												<div class="modal fade" id="modalLiveDemo${loop.index}"
													tabindex="-1" aria-labelledby="exampleModalLabel"
													aria-hidden="true">
													<div class="modal-dialog modal-xl modal-dialog-centered">
														<div class="modal-content">
															<iframe id="player" width="100%" height="600"
																src="https://www.youtube.com/embed/${episode.href1}"
																frameborder="0" allowfullscreen></iframe>
														</div>
													</div>
												</div>

												<form id="episodeForm" action="episodedelete" method="post">
													<input type="hidden" name="confirmation" id="confirmDelete"
														value="false" /> <input type="hidden" id="episodeId"
														name="Id">
												</form>

												<form id="editForm" action="episodeedit" method="get">
													<input type="hidden" id="episodeEditId" name="Id">
												</form>

											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
						</div>

						<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center">
								<c:if test="${currenPage == 1}">
									<li class="page-item text-secondary disabled"><a
										class="page-link" href="#" aria-disabled="true"> <i
											class="ti ti-chevron-left"></i>
									</a></li>
								</c:if>

								<c:if test="${currenPage > 1}">
									<li class="page-item text-secondary"><a class="page-link"
										href="episodeviews?page=${currenPage - 1}" aria-disabled="true">
											<i class="ti ti-chevron-left"></i>
									</a></li>
								</c:if>

								<c:forEach varStatus="i" begin="1" end="${maxPage}">
									<li
										class="page-item text-secondary ${currenPage == i.index ? 'active' : ''}">
										<a class="page-link"
										href="<c:url value='episodeviews?page=${i.index}'/>">${i.index}</a>
									</li>
								</c:forEach>

								<c:if test="${currenPage == maxPage}">
									<li class="page-item text-secondary disabled"><a
										class="page-link" href="#" aria-disabled="true"> <i
											class="ti ti-chevron-right"></i>
									</a></li>
								</c:if>

								<c:if test="${currenPage < maxPage}">
									<li class="page-item text-secondary"><a class="page-link"
										href="episodeviews?page=${currenPage + 1}" aria-disabled="true">
											<i class="ti ti-chevron-right"></i>
									</a></li>
								</c:if>
							</ul>
						</nav>

					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/views/admin/common/footer.jsp"%>

	<%
	Boolean addVideoSuccess = (Boolean) session.getAttribute("addVideoSuccess");

	if (addVideoSuccess != null) {
		if (addVideoSuccess) {
	%>
	<script>
		showSwalAlert('success', 'Thêm episode thành công !');
	</script>
	<%
	} else {
	%>
	<script>
		showCenterAlert('error', 'Thất Bại !', 'Video đã tồn tại trong cơ sở dữ liệu !');
	</script>
	<%
	}
	session.removeAttribute("addVideoSuccess");
	}
	%>

	<script type="text/javascript">
		// lấy href	sử dụng cho edit episode
		function editEpisodeGetId(Id) {
			document.getElementById("episodeEditId").value = Id;
			document.getElementById("editForm").submit();
		}
		
		function deleteEpisode(href) {
			Swal.fire({
				title: 'Cảnh Báo !',
				text: "Bạn có chắc chắn ngưng công chiếu phim không ?",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Đồng ý !'
			}).then((result) => {
				if (result.isConfirmed) {
					document.getElementById("confirmDelete").value = "true";
					Swal.fire(
						'Thành công !',
						'Đổi trạng thái phim thành công !',
						'success'
					).then(() => {
						document.getElementById("episodeId").value = href;
						document.getElementById("episodeForm").submit();
					});
				}
			});

			return false;
		}
	</script>

</body>

</html>