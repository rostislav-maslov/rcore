<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
    <div class="col-lg-12">
        <div class="widget widget-green" id="widget_profit_chart">
            <div class="widget-title">
                <h3><i class="fa fa-exclamation"></i> Внимание, Вы собираетесь удалить файл! </h3>
            </div>
            <div class="widget-content">

                <form:form method="POST">
                    <input type="hidden" value="${id}" name="id"/>
                    <div class="row">
                        <div class="col-lg-12">
                            <h2>Подтвердите удаление!</h2>
                            <h3>Вы собираетесь удалить файл
                                <br/><a href="/files/${id}">/file/${id}</a>
                                <br/>Восстановить файл будет невозможно!
                            </h3>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <button type="submit" id="contact-form-settings-submit" class="btn btn-primary">Подтвердить
                        </button>
                    </div>
                </form:form>

            </div>
        </div>
    </div>
</div>
