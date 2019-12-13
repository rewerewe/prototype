const BTN_PROCESS = document.querySelector('#btnProcess');
const BTN_CLEAR = document.querySelector('.btnClear');
const BTN_INPUT_ALL = document.querySelectorAll('.btnInput');
const BTN_OPERATOR_ALL = document.querySelectorAll('.btnOperator');
const BTN_RESULT = document.querySelector('.btnResult');
let PROCESS_AND_OPERATOR_TXT = '';
let PROCESS_TXT1 = '';
let OPERATOR_TXT = '';
let PROCESS_TXT2 = '';
let isOnOperating = false;

function doBlink(target) {
    // 이전 속성 삭제.
    target.classList.remove('blink');

    // 이전 속성 다시 동적 추가
    setTimeout(function() {
        target.classList.add('blink');
    }, 100);
}

function clearVariables(val1, val2, val3, val4, val5, val6) {
    PROCESS_AND_OPERATOR_TXT = val1;
    PROCESS_TXT1 = val2;
    OPERATOR_TXT = val3;
    PROCESS_TXT2 = val4;

    isOnOperating = val5;

    if (val6) {
        BTN_PROCESS.innerText = val6;
    }
}

function showResult() {
    // 사용자 조작의 의한 모든 결과를 조합.
    PROCESS_AND_OPERATOR_TXT = PROCESS_TXT1 + OPERATOR_TXT + PROCESS_TXT2;

    // eval() : 문자열을 js 수식으로 변경.
    BTN_PROCESS.innerText = eval(PROCESS_AND_OPERATOR_TXT);

    // 다음 연산을 위해 변수의 값 모두를 초기화해야 함.
    clearVariables('', '', '', false, '');

    // 단, 이전 연산의 결과는 유지해야 함.
    PROCESS_TXT1 = BTN_PROCESS.innerText;
}

function btnClearClick(event) {
    doBlink(event.target);

    clearVariables('', '', '', '', false, '0');
}

function btnInputClick(event) {
    doBlink(event.target);

    // 사용자가 연산자 버튼을 누른 상태라면
    if (isOnOperating) {
        PROCESS_TXT2 = event.target.textContent;

        showResult();
    } else {
        PROCESS_TXT1 += event.target.textContent;

        BTN_PROCESS.innerText = PROCESS_TXT1;
    }
}

function btnOperatorClick(event) {
    doBlink(event.target);

    OPERATOR_TXT = event.target.textContent;

    isOnOperating = true;
}

function btnResultClick(event) {
    showResult();
}

function init() {
    BTN_CLEAR.addEventListener('click', btnClearClick);

    // 1. 여러 element에 이벤트 리스너 추가 방법1
    for (let i = 0; i < BTN_INPUT_ALL.length; i++) {
        let BTN_INPUT = BTN_INPUT_ALL[i];
        BTN_INPUT.addEventListener('click', btnInputClick);
    }

    // 2. 여러 element에 이벤트 리스너 추가 방법2
    // BTN_INPUT_ALL.forEach(BTN_INPUT => {
    //     BTN_INPUT.addEventListener('click', btnInputClick);
    // });

    // 2. 여러 element에 이벤트 리스너 추가 방법2
    BTN_OPERATOR_ALL.forEach(BTN_OPERATOR => {
        BTN_OPERATOR.addEventListener('click', btnOperatorClick);
    });

    BTN_RESULT.addEventListener('click', btnResultClick);
}

init();