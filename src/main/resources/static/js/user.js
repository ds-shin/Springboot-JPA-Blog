let index = {
  init: function () {
    // 1.this 값
    // 회원가입완료 클릭시 호출
    $("#btn-save").on("click", () => { // 바인딩처리!!!
      // 2. this 값
      // function(){} 을 사용하지 않고 ()=>{} 를 사용한 이유는 : this 를 바인딩 해주기위함!!!
      // 만약 function(){} 을 사용하면 1번째 this 와 2번째 this 값이 달라진다. 2번째 this 는 window 객체를 가리키게된다.
      this.save(); // 1번과 2번은 동일한 this 값을 갖는다.
    });
    /*
    // function를 사용해야한다면 this 를 변수에 담아서 사용한다.
    let _this = this;
    $("#btn-save").on("click", function(){
      _this.save();
    });
    */
  },

  save: function(){
    //alert("user의 save함수 호출됨");
    let data = {
      username: $("#username").val(),
      password: $("#password").val(),
      email:$("#email").val()
    }
    console.log("data=",data);

    // ajax호출시 default가 비동기호출
    // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
    $.ajax({
      type : "POST",
      url : "/blog/api/user",
      data: JSON.stringify(data), // javasciprt 오브젝트를 json 문자열로 변환하여 전달한다.
      contentType : "application/json; charset=utf-8", // body데이터가 어떤타입인지(mime)
      dataType:"json" // 요청을 서버로해서 응답이왔을때 기본적으로 모든것이 문자열(생긴게 json 이라면)=>javascript object 로 변경해줌
    }).done(function(resp){ // javascript object로 넘겨줌
      alert("회원가입이 완료되었습니다.");
      //console.log(resp);
      location.href="/blog";
    }).fail(function (error){
      alert(JSON.stringify(error));
    });

  }
}

//초기화 설정
index.init();

