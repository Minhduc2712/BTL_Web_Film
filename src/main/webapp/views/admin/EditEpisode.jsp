<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FilmViet - Thêm Video</title>

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
						<h5 class="card-title fw-semibold mb-4 mt-2">Sửa Phim</h5>
						<div class="card">
							<div class="card-body">
								<form id="ConfirmEditForm" action="episodeedit"
									onsubmit="return editEpisode()" method="post">
									<input type="hidden" name="Id" value="${episode.id}">
									<div class="mb-3">
										<label for="movies">Movies:</label><br>
								        <select id="movieTitle" name="movieTitle" >
							            <option value="">-- Select a Movie --</option>
							            <c:forEach var="movie" items="${movies}">
							                <option value="${movie.id}" ${movie.title == param.selectedMovieTitle ? 'selected' : ''}>
							                    ${movie.title}
							                </option>
							            </c:forEach>
							        </select>
							        <br>
							        <div class="mb-3">
										<label for="episodeNumber" class="form-label">Tập</label> <input
											type="text" class="form-control" name="episodeNumber" id="episode-number" value="${episode.episodeNumber}"
											placeholder="Tập...">
									</div>
									<div class="mb-3">
										<label for="title" class="form-label" >Tên tập</label> <input
											type="text" class="form-control" name="title" id="title"
											value="${episode.title}"
											placeholder="Tên tập...">
									</div>
									<div class="mb-3">
										<label for="link-phim" class="form-label">Link phim1</label> <input
											type="text" class="form-control" name="href1" id="link-phim"
											value="${episode.href1}"
											placeholder="Href..." onblur="fillHrefOnPoster()">
									</div>
									<div class="mb-3">
										<label for="link-phim" class="form-label">Link phim2</label> <input
											type="text" class="form-control" name="href2" id="link-phim"
											value="${episode.href2}"
											placeholder="Href..." onblur="fillHrefOnPoster()">
									</div>
									<div class="mb-3">
										<label for="link-phim" class="form-label">Link phim3</label> <input
											type="text" class="form-control" name="href3" id="link-phim"
											value="${episode.href3}"
											placeholder="Href..." onblur="fillHrefOnPoster()">
									</div>
									
									</div>
									<div class="mb-3">
										<label for="noted" class="form-label">Nội dung</label>
										<textarea class="form-control" id="noted"
											 name="noted"
											rows="5" placeholder="Nội dung...">${episode.description}</textarea>
									</div>
									<input type="hidden" id="confirmEdit" value="false" />

									<button type="submit" class="btn btn-success">Xác nhận</button>
									<button type="reset" class="btn btn-primary">Làm mới</button>
									<a class="btn btn-danger float-end" href="episodeAllviews"
										role="button">Trở về</a>

								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/views/admin/common/footer.jsp"%>

	<script type="text/javascript">
		function fillHrefOnPoster() {
			var href = document.getElementById("link-phim").value;
			var poster = document.getElementById("poster");
			poster.value = "https://img.youtube.com/vi/" + href
					+ "/maxresdefault.jpg";
		}
		
		function editEpisode() {
			Swal.fire({
				title: 'Cảnh Báo !',
				text: "Bạn có chắc chắn chỉnh sửa video không ?",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Đồng ý !'
			}).then((result) => {
				if (result.isConfirmed) {
					document.getElementById("confirmEdit").value = "true";
					Swal.fire(
						'Thành công !',
						'Chỉnh sửa video thành công !',
						'success'
					).then(() => {
						document.getElementById("ConfirmEditForm").submit();
					});
				}
			});

			return false;
		}
	</script>

</body>

</html>