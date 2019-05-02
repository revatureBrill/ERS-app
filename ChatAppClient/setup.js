const views = {
    loginView: document.getElementById('login-module'),
    signupView: document.getElementById('signup-module'),
    chatView: document.getElementById('chat-module')
}

const server = 'http://localhost:8080/ChatApp';

const transitionView = function(view) {
    const views = document.querySelectorAll('.module');
    const alerts = document.querySelectorAll('.alert');

    
    for(let i = 0; i < views.length; i++) {
        views[i].classList.remove('active-module');
    }
    for(let i = 0; i < alerts.length; i++) {
        alerts[i].innerText = '';
    }
    view.classList.add('active-module');

}

document.getElementById('signup-nav').addEventListener('click', () => {
    transitionView(views.signupView);
});

document.getElementById('login-nav').addEventListener('click', () => {
    transitionView(views.loginView);
});

const signup = function() {
    const username = document.getElementById('signup-username');
    const password = document.getElementById('signup-password');
    const alert = document.getElementById('signup-alert');
    const payload = {
        username: username.value,
        password: password.value
    };

    const xhr = new XMLHttpRequest();
    xhr.open('post', `${server}/signup`);
    
    xhr.addEventListener('load', () => {
        alert.innerText = 'Signup complete!';
    })

    xhr.addEventListener('error', () => {
        alert.innerText = 'Something went wrong :(';
    });
    
    xhr.send(JSON.stringify(payload));
}

const login = function() {
    const username = document.getElementById('login-username');
    const password = document.getElementById('login-password');
    const alert = document.getElementById('login-alert');
    const payload = {
        username: username,
        password: password
    };

    const xhr = new XMLHttpRequest();
    xhr.open('post', `${server}/login`);
    
    xhr.addEventListener('load', () => {
    })

    xhr.addEventListener('error', () => {
        alert.innerText = 'Something went wrong :(';
    });
    
    xhr.send(JSON.stringify(payload));
}

document.getElementById('login-submit').addEventListener('click', login);
document.getElementById('signup-submit').addEventListener('click', signup);