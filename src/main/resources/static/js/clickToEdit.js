"use strict";
function clickToEdit(id) {
    let form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', "/edit");
    let hiddenField = getHiddenIdInput(id);
    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
}
function clickToDelete(id) {
    console.log("된다??");
    const answer = confirm("정말로 삭제하시겠습니까?");
    if (!answer)
        return;
    let form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', "/delete");
    let hiddenField = getHiddenIdInput(id);
    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
}
function getHiddenIdInput(id) {
    let hiddenField = document.createElement('input');
    hiddenField.setAttribute('type', 'hidden');
    hiddenField.setAttribute('name', 'module_id');
    hiddenField.setAttribute('value', String(id));
    return hiddenField;
}
