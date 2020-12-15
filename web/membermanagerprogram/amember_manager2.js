document.write("<script type='text/javascript' src='amember_manager1.js'><"+"/script>");

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