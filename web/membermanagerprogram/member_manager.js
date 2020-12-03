// 회원정보 Member 객체들을 저장하는 배열
var members = []; // object -> 문자열 JSON

// 생성자 함수 Member

function Member(name, id, pw) {
    this.username = name;
    this.userid = id;
    this.userpw = pw;
};

Member.prototype.makeHtml = function(index){
    
    console.log(this.username+' : '+this.userid+' : '+this.userpw);
    
    var memberHtml = '';
    memberHtml += '<tr>';
    memberHtml += ' <td>' + index + '</td>';
    memberHtml += ' <td>' + this.username + '</td>';
    memberHtml += ' <td>' + this.userid + '</td>';
    memberHtml += ' <td>' + this.userpw + '</td>';
    memberHtml += ' <td><a>수정</a> <a href="javascript:delmember('+index+')">삭제</a></td>';
    memberHtml += '</tr>';
    
    return memberHtml;
    
};



window.onload = function(){
    
    // localStorage 저장
    if(localStorage.getItem('members')==null) {
        localStorage.setItem('members', JSON.stringify(members));
    } else {
        members = JSON.parse(localStorage.getItem('members'));
        
        console.log(members);
        
        setlist();
    }
    
};




function regmember() {
    
    // 사용자가 입력한 데이터 확인
    // 유효성 검사
    // 사용자 정보를 저장하는 객체를 생성
    
    
    // 이름
    var username = document.querySelector('#username');
    // 아이디
    var userid = document.querySelector('#userid');
    // 비밀번호
    var pw = document.getElementById('pw');
    // 비밀번호 확인
    var repw = document.getElementById('repw');
    
    var check = false; // 문제 없다
    
    // 공백문자 검사
    if (username.value.trim().length < 1) {
        document.querySelector('#username+div.msg').style.display = 'block';
        check = true;
    }
    if(userid.value.trim().length<1) {
        document.querySelector('#userid+div.msg').style.display = 'block';
        check = true;
        
    }
    if (pw.value.trim().length < 1) {
        document.querySelector('#pw+div.msg').style.display = 'block';
        check = true;
    }
    if (repw.value.trim().length < 1 || pw.value != repw.value) {
        document.querySelector('#repw+div.msg').style.display = 'block';
        check = true;
    }
    
    
    username.addEventListener('focus', function(){
        document.querySelector('#username+div.msg').style.display = 'none';
        username.value = '';
    });
    userid.addEventListener('focus', function(){
        document.querySelector('#userid+div.msg').style.display = 'none';
        userid.value = '';
    });
    pw.addEventListener('focus', function(){
        document.querySelector('#pw+div.msg').style.display = 'none';
        pw.value = '';
    });
    repw.addEventListener('focus', function(){
        document.querySelector('#repw+div.msg').style.display = 'none';
        repw.value = '';
    });
    
    if(check){
        return false;
    }
    
    var member = new Member(username.value, userid.value, pw.value);
    
    
    member.makeHtml()
    
    // 배열에 회원 정보 저장
    members.push(member);
    
    console.log('member', member);
    
    document.querySelector('#regform').reset();
    
    alert('등록완료');
    
    // localStorage 저장
    localStorage.setItem('members', JSON.stringify(members));
    
    // 리스트 갱신
    setlist();
    
    
    return false;
    
    
};



// 리스트 테이블의 정렬
function setlist() {
    
    // table tbody 캐스팅
    var listrow = document.querySelector('#listrow');
    
    var tbody = '<tr>';
    tbody += '<th>순번(index)</th>';
    tbody += '<th>이름(index)</th>';
    tbody += '<th>아이디(index)</th>';
    tbody += '<th>비밀번호(index)</th>';
    tbody += '<th>관리(index)</th>';
    tbody += '</tr>';
    
    for (var i=0; i<members.length; i++) {
        
        tbody += '<tr>';
        tbody += ' <td>' + i + '</td>';
        tbody += ' <td>' + members[i].username + '</td>';
        tbody += ' <td>' + members[i].userid + '</td>';
        tbody += ' <td>' + members[i].userpw + '</td>';
        tbody += ' <td><a href="javascript:editMember('+i+')">수정</a> <a href="javascript:delmember('+i+')">삭제</a></td>';
        tbody += '</tr>';
        
    }
    
    listrow.innerHTML = tbody;
    
};

// 배열의 데이터 삭제
function delmember(index) {
    
    var delChk = confirm('삭제하시겠습니까?');
    
    if(delChk){
        
        // 삭제
        members.splice(index, 1);
        
        // localStorage 저장
        localStorage.setItem('members', JSON.stringify(members));
        
        alert('삭제완료');
        
        setlist();
    }
    
};

function editMember(index) {
    
    // 수정 화면 캐스팅
    var editDiv = document.querySelector('div.edit_div');
    editDiv.style.display = 'block';
    
    // form 안에 있는 input 캐스팅
    // name
    var ename = document.querySelector('#editname');
    // id
    var eid = document.querySelector('#editid');
    // pw
    var epw = document.querySelector('#editpw');
    // repw
    var erepw = document.querySelector('#editrepw');
    // index
    var idx = document.querySelector('#index');
    
    idx.value = index;
    ename.value = members[index].username;
    eid.value = members[index].userid;
    epw.value = members[index].userpw;
    erepw.value = members[index].userpw;
    
};

function editMemberData() {
    // index
    var eidx = document.querySelector('#index').value;
    // name
    var ename = document.querySelector('#editname');
    // pw
    var epw = document.querySelector('#editpw');
    // repw
    var erepw = document.querySelector('#editrepw');
    
    var ename_msg = document.querySelector('#editname+div.msg');
    var pw_msg = document.querySelector('#editpw+div.msg');
    var repw_msg = document.querySelector('#editrepw+div.msg');
    
    
    var check = false; // 문제없음
    
    if (ename.value.trim().length < 1) {
        ename_msg.style.display = 'block';
        check = true;
    }
    if (epw.value.trim().length < 1) {
        pw_msg.style.display = 'block';
        check = true;
    }
    
    /*@비밀번호 일치 동작 오류: 미해결*/
    if (erepw.value.trim().length < 1 || pw.value != repw.value) {
        repw_msg.style.display = 'block';
        check = true;
    }
    
    ename.addEventListener('focus', function () {
        ename_msg.style.display = 'none';
        ename.value = '';
    });
    epw.addEventListener('focus', function () {
        pw_msg.style.display = 'none';
        epw.value = '';
    });
    erepw.addEventListener('focus', function () {
        repw_msg.style.display = 'none';
        erepw.value = '';
    });

    if (check) {
        return false;
    }
    
    // members[index] -> pw, name 수정
    members[eidx].username = ename.value.trim();
    members[eidx].userpw = epw.value;
    
    // localStorage 저장
    localStorage.setItem('members', JSON.stringify(members));
    
    alert('수정 완료');
    
    setlist();
    
    document.querySelector('#editform').reset();
    
    document.querySelector('div.edit_div').style.display = 'none';
    
    return false;
    
    
};

function editMemberClose() {
    var editDiv = document.querySelector('div.edit_div');
    
    editDiv.style.display = 'none';
};




















