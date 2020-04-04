<%@ page import="com.ub.core.user.routes.UserAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!-- Modal 3 (Custom Width)-->
<div class="modal fade custom-width" id="modal-user-search">
    <input type="hidden" id="modal-user-search-input-id"/>
    <input type="hidden" id="modal-user-search-input-name"/>
    <input type="hidden" id="modal-user-search-input-current-page" value="0"/>

    <div class="modal-dialog" style="width: 96%">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Выбор пользователя</h4>
            </div>

            <div class="modal-body">

                <div class="row">
                    <div class="col-lg-5">
                        <div class="input-group">
                            <input type="text" class="form-control input-sm" id="modal-user-search-query"
                                   placeholder="Поиск"/>

                            <div class="input-group-btn">
                                <button class="btn btn-sm btn-default" onclick="modalUserSearchUpdateContent(0);">
                                    <i class="entypo-search">Поиск </i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <section id="modal-user-search-table"></section>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<script>
    function modalUserSearchChoseUser(id, name) {
        $($('#modal-user-search-input-id').val()).val(id);
        $($('#modal-user-search-input-name').val()).val(name);

        $("#modal-user-search").modal('hide');
    }

    function modalUserSearchUpdateContent(currentPage) {
        $('modal-user-search-input-current-page').val(currentPage);
        $.get("<%=UserAdminRoutes.LIST_MODAL_SEARCH%>",
                {
                    query: $('#modal-user-search-query').val(),
                    currentPage: $('#modal-user-search-input-current-page').val()
                },
                function (data) {
                    $('#modal-user-search-table').html(data);
                }
        );
    }

    function openModalUserSearch(jInputId, jInputName) {
        $('#modal-user-search-input-id').val(jInputId);
        $('#modal-user-search-input-name').val(jInputName);

        $("#modal-user-search").modal('show');
        modalUserSearchUpdateContent(0);
    }
</script>