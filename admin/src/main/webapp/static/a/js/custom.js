$(function () {
    $('.js-go-to-top').click(function () {
        $("html, body").animate({ scrollTop: 0 }, "fast");
        return false;
    });
});