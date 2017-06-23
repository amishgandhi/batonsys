<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<table class="table table-striped">
			<caption>Your clients are</caption>
			<thead>
				<tr>
					<th>Name</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${clients}" var="client">
					<tr>
						<td>${client.name}</td>
						<c:if test="${client.connected}">
							<td><a type="button" class="btn btn-success"
								href="toggle-client?id=${client.id}">Disconnect</a></td>						
						</c:if>
						<c:if test="${not client.connected}">
							<td><a type="button" class="btn btn-success"
								href="toggle-client?id=${client.id}">Connect</a></td>						
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="/add-client">Add a Client</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>