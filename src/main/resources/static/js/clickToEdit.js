"use strict";
//기기 조회 페이지에서 각 행을 누르면 각 기기 별 원하는 페이지로 이동
function clickToEdit(id) {
    createIdSubmit("/edit", id);
}
//삭제 submit 확인 및 제출 수행
function clickToDelete(id) {
    //확인 알림창
    const answer = confirm("정말로 삭제하시겠습니까?");
    if (!answer)
        return;
    createIdSubmit("/delete", id);
}
function createIdSubmit(action, id) {
    //백엔드로 넘기기 위한 form 생성
    let form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', action);
    let hiddenField = getHiddenIdInput(id); //화면상에 드러나지 않도록 함, 아이디만 넘겨줌
    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit(); //제출
}
function checkEditSubmit() {
    //확인 알림창
    const answer = confirm("정말로 수정하시겠습니까?");
    if (!answer)
        return false; //submit 안됨
    else
        return true; //submit 실행
}
//표시되지 않는 id input DOM 생성
function getHiddenIdInput(id) {
    //DOM 생성 및 Attribute 설정
    let hiddenField = document.createElement('input');
    hiddenField.setAttribute('type', 'hidden');
    hiddenField.setAttribute('name', 'module_id');
    hiddenField.setAttribute('value', String(id));
    return hiddenField; //DOM 리턴
}
//등록 페이지에서 값이 모두 입력되었는지 확인
function checkRegisterSubmit() {
    //type select DOM에서 선택된 값 가지고 오기
    const typeSelectDOM = document.querySelectorAll("select")[0];
    const typeData = typeSelectDOM.options[typeSelectDOM.selectedIndex].value;
    //선택되지 않았다면
    if (typeData == "haveToSelect") {
        alert("모듈 종류를 선택하세요");
        return false; //submit 안됨
    }
    //아무것도 입력하지 않았다면
    const nameData = document.getElementById("module_name-text").value;
    if (nameData == "") {
        alert("모듈 이름을 입력하세요");
        return false; //submit 안됨
    }
    //아무것도 입력하지 않았다면
    const ownerData = document.getElementById("module_owner-text").value;
    if (ownerData == "") {
        alert("소유자명을 입력하세요");
        return false; //submit 안됨
    }
    return true; //제출
}
