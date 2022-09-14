
<%@page import="com.ecommerce.entities.Message"%>
<%
Message message = (Message) session.getAttribute("status");
if (message != null) {
%>
<div class="alert <%=message != null ? message.getCssClass() : ""%> alert-dismissible fade show" role="<%=message != null ? message.getCssClass() : ""%>">
  <strong><%=message != null ? message.getContent() : ""%></strong> 
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
<%}
session.removeAttribute("status");
%>

