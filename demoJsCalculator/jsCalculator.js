const BTN_PROCESS = document.querySelector('#btnProcess');
const BTN_CLEAR = document.querySelector('.btnClear');
const BTN_INPUT_ALL = document.querySelectorAll('.btnInput');
const BTN_OPERATOR_ALL = document.querySelectorAll('.btnOperator');
const BTN_OPERATOR_MUL_ALL = document.querySelectorAll('.btnOperatorMul');
const BTN_RESULT = document.querySelector('.btnResult');
let operatingMode = 0;
let processArr = [];

function doBlink(target) {
    // 이전 속성 삭제.
    target.classList.remove('blink');

    // 이전 속성 다시 동적 추가.
    setTimeout(function () {
        target.classList.add('blink');
    }, 100);
}

function btnClearClick(event) {
    doBlink(event.target);

    BTN_PROCESS.innerText = '0';

    processArr = [];
}

function btnInputClick(event) {
    doBlink(event.target);

    let currentVal = event.target.textContent;
    processArr.push(currentVal);
    
    if (operatingMode == '0') { // 사용자가 추가 숫자 버튼을 누른 상태
        BTN_PROCESS.innerText = processArr.join('');
    } else if (operatingMode == '1') { // 사용자가 연산자 버튼을 누른 상태
        // BTN_PROCESS.innerText = processArr.join('');
        BTN_PROCESS.innerText = currentVal;

        operatingMode = '0';
    } else { // 사용자가 * 연산자 버튼을 누른 상태
        BTN_PROCESS.innerText = eval(processArr.join(''));
    }
}

function btnOperatorClick(event) {
    doBlink(event.target);

    let currentVal = event.target.textContent;
    processArr.push(currentVal);

    if (currentVal != '*') {
        operatingMode = '1';
    } else {
        operatingMode = '2';
    }
}

function btnResultClick(event) {
    BTN_PROCESS.innerText = eval(processArr.join(''));

    processArr = [];
    processArr[0] = BTN_PROCESS.innerText;
}

function init() {
    BTN_CLEAR.addEventListener('click', btnClearClick);

    // 1. 여러 element에 이벤트 리스너 추가 방법1
    for (let i = 0; i < BTN_INPUT_ALL.length; i++) {
        let BTN_INPUT = BTN_INPUT_ALL[i];
        BTN_INPUT.addEventListener('click', btnInputClick);
    }

    // 2. 여러 element에 이벤트 리스너 추가 방법2
    BTN_OPERATOR_ALL.forEach(BTN_OPERATOR => {
        BTN_OPERATOR.addEventListener('click', btnOperatorClick);
    });

    // 2. 여러 element에 이벤트 리스너 추가 방법2
    BTN_OPERATOR_MUL_ALL.forEach(BTN_OPERATOR => {
        BTN_OPERATOR.addEventListener('click', btnOperatorClick);
    });

    BTN_RESULT.addEventListener('click', btnResultClick);
}

init();