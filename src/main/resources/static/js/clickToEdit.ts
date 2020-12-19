function clickToEdit(id: number) {
    let form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', "/edit");

    let hiddenField = getHiddenIdInput(id);

    form.appendChild(hiddenField);
    document.body.appendChild(form);

    form.submit();
}

function clickToDelete(id: number) {
    const answer = confirm("정말로 삭제하시겠습니까?");

    if (!answer) return;

    let form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', "/delete");

    let hiddenField = getHiddenIdInput(id);

    form.appendChild(hiddenField);
    document.body.appendChild(form);

    form.submit();
}

function checkEditSubmit() {
    const answer = confirm("정말로 수정하시겠습니까?");

    if (!answer) return false;
    else return true;
}

function getHiddenIdInput(id: number) {
    let hiddenField = document.createElement('input');
    hiddenField.setAttribute('type', 'hidden');
    hiddenField.setAttribute('name', 'module_id');
    hiddenField.setAttribute('value', String(id));

    return hiddenField;
}

function checkRegisterSubmit() {
    const typeSelectDOM = document.querySelectorAll("select")![0];
    const typeData = typeSelectDOM.options[typeSelectDOM.selectedIndex].value;

    if(typeData=="haveToSelect") {
        alert("모듈 종류를 선택하세요");
        return false;
    }

    const nameData = (<HTMLInputElement>document.getElementById("module_name-text")).value;
    if(nameData=="") {
        alert("모듈 이름을 입력하세요");
        return false;
    }

    const ownerData = (<HTMLInputElement>document.getElementById("module_owner-text")).value;
    if(ownerData=="") {
        alert("소유자명을 입력하세요");
        return false;
    }


    return true;
}
