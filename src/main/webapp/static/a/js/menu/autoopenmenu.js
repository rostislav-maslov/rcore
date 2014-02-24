function autoOpenMenu() {
    var allA = $('#main-menu > li >  ul > li > a');
    var currLink = window.location.pathname;
    for (var i = 0;i< allA.length; i++) {
        try {
            if(currLink.indexOf($(allA[i]).attr('href')) != -1){
                $(allA[i]).parent().addClass('active');
                $(allA[i]).parent().parent().parent().addClass('opened');
            }
        } catch (exception_var) {
        }
    }
}

$(function(){autoOpenMenu();});