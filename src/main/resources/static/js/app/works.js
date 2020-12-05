var main = {
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function(){
            console.log("save");
           _this.save();
        });
        $('#btn-update').on('click', function(){
            console.log("update");
            _this.update();
        });
        $('#btn-delete').on('click', function (){
            console.log("deleete");
            _this.delete();
        })
    },
    save: function () {
        var data = {
            title: $('#title').val(),
            author: $('#autor').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/works',
            // dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/contents/works';
        }).fail(function (e) {
           alert(JSON.stringify(e));
        });
    },
    update: function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/works/'+id,
            // dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/contents/works';
        }).fail(function (e) {
            alert(JSON.stringify(e));
        });
    },
    delete: function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/works/'+id,
            contentType:'application/json; charset=utf-8',
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href = '/contents/works';
        }).fail(function (e){
            alert(JSON.stringify(e));
        });
    }
};

main.init();