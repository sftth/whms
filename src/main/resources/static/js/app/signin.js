var signin = {
    init: function () {
        var _this = this;
        $('#btn-signin').on('click', function(){
            console.log("signIn");
            _this.signin();
        });
    },
    signin: function () {
        var data = {
            email: $('#email').val(),
            passwd: $('#passwd').val()
        };

        $.ajax({
            type: 'POST',
            url: '/main/api/v1/signin',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            console.log("signin is done.");
            window.location.href = '/contents/main';
        }).fail(function (e){
           alert(JSON.stringify(e));
        });
    }

};

signin.init();