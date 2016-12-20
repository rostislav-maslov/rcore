<%@ page import="com.ub.core.pictureTask.routes.PictureTaskAdminRoutes" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="modal fade custom-width" id="modal-pictureTask">
    <div class="modal-dialog" style="width: 96%">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Выбор Picture Tasks</h4>
            </div>

            <div class="modal-body">

                <div class="row">

                    <div class="col-lg-5">
                        <div class="input-group">
                            <input type="text" class="form-control input-sm" id="modal-pictureTask-query" name="query"
                                   value="" placeholder="Поиск"/>

                            <div class="input-group-btn">
                                <button type="submit" id="modal-pictureTask-search" class="btn btn-sm btn-default"><i
                                        class="entypo-search">Поиск </i>
                                </button>
                            </div>
                        </div>
                    </div>

                </div>
                <section id="modal-pictureTask-parent-content"></section>

            </div>
        </div>
    </div>
</div>

<script>
    function initPictureTaskModal() {
        $.get("<%= PictureTaskAdminRoutes.MODAL_PARENT%>",
                {query: $('#modal-pictureTask-query').val()},
                function (data) {
                    updatePictureTaskContent(data);
                });
    }

    function updatePictureTaskContent(data) {
        $('#modal-pictureTask-parent-content').html(data);
    }

    function onClickPictureTaskMTable() {
        var id = $(this).attr('data-id');
        var title = $(this).attr('data-title');
        $('#parent-pictureTask-hidden').val(id);
        $('#parent-pictureTask').val(title);
        $('#modal-pictureTask').modal('hide');
    }

    function onClickPictureTaskPaginator() {
        var q = $(this).attr('data-query');
        var n = $(this).attr('data-number');
        $.get("<%= PictureTaskAdminRoutes.MODAL_PARENT%>",
                {query: q, currentPage: n},
                function (data) {
                    updatePictureTaskContent(data);
                });
    }

    $(function () {
        $('#btn_parent_pictureTask').click(function () {
            $('#modal-pictureTask').modal('show');
            initPictureTaskModal();
            return false;
        });
        $('#btn_parent_pictureTask_clear').click(function () {
            $('#parent-pictureTask-hidden').val('');
            $('#parent-pictureTask').val('');
            return false;
        });

        $('#modal-pictureTask').on('click', '.modal-pictureTask-line', onClickPictureTaskMTable);
        $('#modal-pictureTask').on('click', '.modal-pictureTask-goto', onClickPictureTaskPaginator);
        $('#modal-pictureTask-search').click(initPictureTaskModal);

    });
</script>
