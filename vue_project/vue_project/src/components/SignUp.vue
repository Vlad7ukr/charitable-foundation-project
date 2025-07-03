<script>
export default {
    data() {
        return {
            form: {
                email: '',
                password: '',
                surname: '',
                name: '',
                thirdname: '',
                birthday: '',
                selectedGender: '',
                selectedGroup: '',
                phone: '',
            },
                
            errors: {
                emailError: '',
                passwordError: '',
                surnameError: '',
                nameError: '',
                thirdnameError: '',
                birthdayError: '',
                genderError: '',
                groupError: '',
                fileError: '',
                phoneError: '',
                buttonError: '',
            },
            hasErrors: false,
            tableShown: false,
            tableUsers: Array(),
            userCount: 0,
            selected: new Set(),
        }
    },

    methods: {
        validateEmail() {
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (this.form.email.length === 0) {
                this.errors.emailError = "Електронна пошта обов'язкова";
                return false
            }
            if (!emailPattern.test(this.form.email)) {
                this.errors.emailError = "Неправильно введені дані";
                return false
            }
            this.errors.emailError = "✅";
            return true
        },
        validatePassword() {
            this.errors.passwordError = '';

            if (this.form.password.length === 0) {
                this.errors.passwordError = "Пароль обов'язковий";
                return false
            } else if (this.form.password.length < 8) {
                this.errors.passwordError = 'Пароль повинен містити щонайменше 8 символів';
                return false
            }

            if (!this.errors.passwordError) {
                this.errors.passwordError = "✅";
                return true
            }
        },
        validateSurname() {
            this.errors.surnameError = '';
            const surnamePattern = /^[A-ZА-ЯЁ][a-zа-яё]{1,}$/;

            if (this.form.surname.length == 0) {
                this.errors.surnameError = "Прізвище обов'язкове";
                return false
            } else if (!surnamePattern.test(this.form.surname)) {
                this.errors.surnameError = "Неправильно введені дані";
                return false
            }
            if (!this.errors.surnameError) {
                this.errors.surnameError = "✅";
                return true
            }
        },
        validateName() {
            this.errors.nameError = '';
            const namePattern = /^[A-ZА-ЯЁ][a-zа-яё]{1,}$/;

            if (this.form.name.length == 0) {
                this.errors.nameError = "Ім'я обов'язкове";
                return false
            } else if (!namePattern.test(this.form.name)) {
                this.errors.nameError = "Неправильно введені дані";
                return false
            }

            if (!this.errors.nameError) {
                this.errors.nameError = "✅";
                return true
            }
        },
        validateThirdname() {
            this.errors.thirdnameError = '';
            const thirdnamePattern = /^[A-ZА-ЯЁ][a-zа-яё]{1,}$/;

            if (this.form.thirdname.length == 0) {
                this.errors.thirdnameError = "По-батькові обов'язкове";
                return false
            } else if (!thirdnamePattern.test(this.form.thirdname)) {
                this.errors.thirdnameError = "Неправильно введені дані";
                return false
            }
            if (!this.errors.thirdnameError) {
                this.errors.thirdnameError = "✅";
                return true
            }
        },
        validateBirthday() {
            
            this.errors.birthdayError = '';

            
            if (this.form.birthday === '') {
                this.errors.birthdayError = "Ви не заповнили дату народження";
                return false
            } else {
                const birthdateDate = new Date(this.form.birthday);
                const currentDate = new Date();

                if (birthdateDate > currentDate) {
                    this.errors.birthdayError = "Неправильна дата (майбутня дата)";
                    return false
                }
            }

            if (!this.errors.birthdayError) {
                this.errors.birthdayError = "✅"
                return true
            }
        },
        validateGender() {
            
            this.errors.genderError = '';

            
            if (!this.form.selectedGender) {
                this.errors.genderError = "Будь ласка, оберіть вашу стать";
                return false
            }

            
            if (!this.errors.genderError) {
                this.errors.genderError = "✅"
                return true
            }
        },
        validateGroup() {
            
            this.errors.groupError = '';

            
            if (this.form.selectedGroup == 'none' || this.form.selectedGroup == '') {
                this.errors.groupError = 'Оберіть вашу групу';
                return false
            }

            
            if (!this.errors.groupError) {
                this.errors.groupError = "✅"
                return true
            }
        },
        validateFile() {
            
            this.errors.fileError = '';

            
            const selectedFiles = this.$refs.fileInput.files;
            if (selectedFiles.length === 0) {
                this.errors.fileError = 'Будь ласка, оберіть файл';
                return false
            }

            
            if (!this.errors.fileError) {
                this.errors.fileError = "✅"
                return true
            }
        },

        validatePhone() {
            
            this.errors.phoneError = '';

            
            const phonePattern = /^\+38\(0[1-9]\d{1}\) \d{3}-\d{2}-\d{2}$/;

            if (this.form.phone.length === 0) {
                this.errors.phoneError = 'Номер телефону обов\'язковий';
                return false
            } else if (!phonePattern.test(this.form.phone)) {
                this.errors.phoneError = 'Неправильно введені дані';
                return false
            }
            if (!this.errors.phoneError) {
                this.errors.phoneError = "✅"
                return true
            }
        },
        submitForm() {
            
            const isFormValid = this.validateForm();

            
            if (isFormValid) {
                this.addUserToTable();
                this.$refs.form.reset();
                this.resetForm();
                console.log('Form submitted successfully!');
                
            }
        },

        resetForm() {
            for (const key in this.form) {
                this.form[key] = ''
            }  
            for (const key in this.errors) {
                this.errors[key] = ''
            }
            this.hasErrors = false
        },
        

        validateForm() {
            

            
            const isEmailValid = this.validateEmail();
            const isPasswordValid = this.validatePassword();
            const isSurnameValid = this.validateSurname();
            const isNameValid = this.validateName();
            const isThirdnameValid = this.validateThirdname();
            const isBirthdayValid = this.validateBirthday();
            const isGenderValid = this.validateGender();
            const isGroupValid = this.validateGroup();
            const isFileValid = this.validateFile();
            const isPhoneValid = this.validatePhone();

            
            if (
                !(
                    isEmailValid &&
                    isPasswordValid &&
                    isSurnameValid &&
                    isNameValid &&
                    isThirdnameValid &&
                    isBirthdayValid &&
                    isGenderValid &&
                    isGroupValid &&
                    isFileValid &&
                    isPhoneValid
                )
            ) {
                this.errors.hasErrors = true;
                this.errors.buttonError = "Будь ласка, виправте помилки";
                return false;
            } else {
                this.errors.hasErrors = false;
                this.errors.buttonError = '';
                return true;
            }
        },

        toggleTable() {
            this.tableShown = !this.tableShown;
        },

        addUserToTable() {
            const formCopy = { ...this.form}
            Object.defineProperties(formCopy, {
                files: {value: this.$refs.fileInput.value},
                id: {value:this.userCount++},
            })
            this.tableUsers.push(formCopy)
        },

        select(e, index) {
            const classes = e.target.parentNode.classList;
            if (classes.contains('selected')) {
                classes.remove('selected')
                this.selected.delete(index)
            } else {
                classes.add('selected')
                this.selected.add(index)
            }
        },

        dublicateUsers() {
            for (const index of this.selected) {
                const userCopy = { ...this.tableUsers[index]}
                userCopy.id = this.userCount++
                this.tableUsers.push(userCopy)
            }
        },

        deleteUsers() {
            for(var i = this.tableUsers.length; i >=0; i-- ) {
                if (this.selected.has(i)) {
                    this.tableUsers.splice(i, 1)
                }
            }
            this.selected.clear();
            console.log(this.selected)
        }

    },

}
</script>

<template>
    <body>
        <div id="app">
            <div class="header">
                <div class="logo-container">
                    <img class="logo-img" src="https://i.obozrevatel.com/gallery/2020/2/17/1024px-alexkkievanrus-svg.png"
                        alt="logo">
                </div>
              <nav>
                <div class="bars">
                  <i class="fa-solid fa-bars"></i>
                </div>
                <ul class="nav-list">
                  <li>
                    <router-link to="/">Головна</router-link>
                  </li>
                  <li>
                    <router-link to="/sponsor">Спонсори</router-link>
                  </li>
                  <li>
                    <router-link to="/donation">Усі донати</router-link>
                  </li>
                  <li>
                    <router-link to="/donor">Донори</router-link>
                  </li>
                  <li>
                    <router-link to="/contacts">Контакти</router-link>
                  </li>
                  <li>
                    <router-link to="/faq">FAQ</router-link>
                  </li>
                  <li>
                    <router-link class="link-with-heart" to="/support">
                      <svg id="heart-img" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="heart" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="fs-s mr-10 svg-inline--fa fa-heart fa-w-16 heart" data-v-4180a957="">
                        <path fill="currentColor" d="M462.3 62.6C407.5 15.9 326 24.3 275.7 76.2L256 96.5l-19.7-20.3C186.1 24.3 104.5 15.9 49.7 62.6c-62.8 53.6-66.1 149.8-9.9 207.9l193.5 199.8c12.5 12.9 32.8 12.9 45.3 0l193.5-199.8c56.3-58.1 53-154.3-9.8-207.9z"
                              data-v-4180a957="" class=""></path></svg>
                      Підтримати

                    </router-link>
                  </li>
                </ul>
              </nav>
            </div>

            <div class="content">
                <section class="signing-up">
                    <div class="form">
                        <form ref="form">
                            <h2>Реєстрація</h2>
                            <label for="email">Email</label>
                            <input type="email" id="email" placeholder="Введіть електронну адресу" v-model="form.email"
                                @input="validateEmail">
                            <span class="error" id="email-error"> {{ errors.emailError }}</span>
                            <br><br>
                            <label for="password">Пароль</label>
                            <input v-model="form.password" type="password" id="password" placeholder="Введіть пароль"
                                @input="validatePassword">
                            <span class="error" v-html="errors.passwordError"></span>
                            <br><br>
                            <label for="surname">Прізвище</label>
                            <input v-model="form.surname" type="text" id="surname" placeholder="Введіть прізвище"
                                @input="validateSurname">
                            <span class="error" v-html="errors.surnameError"></span>
                            <br> <br>
                            <label for="name">Ім'я</label>
                            <input v-model="form.name" type="text" id="name" placeholder="Введіть ім'я"
                                @input="validateName">
                            <span class="error" v-html="errors.nameError"></span>
                            <br> <br>
                            <label for="thirdname">По батькові</label>
                            <input v-model="form.thirdname" type="text" id="thirdname" placeholder="Введіть по батькові"
                                @input="validateThirdname">
                            <span class="error" v-html="errors.thirdnameError"></span>
                            <br> <br>
                            <label for="birthday">Дата народження</label>
                            <input v-model="form.birthday" type="date" id="birthday" placeholder="Введіть дату народження"
                                @input="validateBirthday">
                            <span class="error" v-html="errors.birthdayError"></span>
                            <br> <br>
                            <span>Вкажіть свою стать:</span>
                            <div class="enterGender">
                                <div>
                                    <label for="male">Чоловік</label>
                                    <input name="gender" type="radio" id="male" v-model="form.selectedGender" value="Чоловік"
                                        @change="validateGender">
                                </div>
                                <div>
                                    <label for="female">Жінка</label>
                                    <input name="gender" type="radio" id="female" v-model="form.selectedGender" value="Жінка"
                                        @change="validateGender">
                                </div>
                                <div>
                                    <label for="notMentioned">Не вказано</label>
                                    <input name="gender" type="radio" id="notMentioned" v-model="form.selectedGender"
                                        value="Не вказано" @change="validateGender">
                                </div>
                                <br>
                            </div>
                            <span class="error" v-html="errors.genderError"></span>
                            <br><br>
                            <span>Оберіть групу</span>
                            <select name="Група" id="group" v-model="form.selectedGroup" @change="validateGroup">
                                <option value="none">Не вказано</option>
                                <option value="ia-24">IA-24</option>
                                <option value="ia-23">IA-23</option>
                                <option value="ia-22">IA-22</option>
                                <option value="ia-21">IA-21</option>
                            </select>
                            <br>
                            <span class="error" v-html="errors.groupError"></span>
                            <br> <br>
                            <label for="fileInput">Виберіть файл:</label>
                            <br>
                            <input class="file-form" type="file" id="fileInput" name="uploadedFile"
                                accept="image/jpeg, image/png" multiple @change="validateFile" ref="fileInput">
                            <span class="error" v-html="errors.fileError"></span>
                            <br> <br>
                            <label for="phoneNumber">Номер телефону</label>
                            <input type="tel" v-mask="'+38(099) 999-99-99'" id="phoneNumber" placeholder="+38(0__) ___-__-__" v-model="form.phone"
                                @input="validatePhone">
                            <span class="error" v-html="errors.phoneError"></span>
                            <br><br>
                            <button id="submitButton" @click.prevent="submitForm">Зареєструватися</button>
                            <span class="error button" v-if="errors.hasErrors">{{ errors.buttonError }}</span>
                        </form>
                    </div>
                    <div v-show="tableShown" class="table">
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Email</th>
                                    <th>Пароль</th>
                                    <th>Прізвище</th>
                                    <th>Ім'я</th>
                                    <th>По батькові</th>
                                    <th>Дата народження</th>
                                    <th>Стать</th>
                                    <th>Група</th>
                                    <th>Файл</th>
                                    <th>Телефон</th>
                                </tr>
                            </thead>
                            <tbody id="users">
                                <tr @click="select($event, index)" v-for="(user, index) in tableUsers" :key="user.id">
                                    <td>{{ user.id }}</td>
                                    <td>{{ user.email }}</td>
                                    <td>{{ user.password }}</td>
                                    <td>{{ user.surname }}</td>
                                    <td>{{ user.name }}</td>
                                    <td>{{ user.thirdname }}</td>
                                    <td>{{ user.birthday }}</td>
                                    <td>{{ user.selectedGender }}</td>
                                    <td>{{ user.selectedGroup }}</td>
                                    <td>{{ user.files }}</td>
                                    <td>{{ user.phone }}</td>
                                </tr>
                            </tbody>
                        </table>
                        <div id="tableButtons">
                            <button id="btnDelete" @click="deleteUsers">Видалити</button>
                            <button id="btnDubble" @click="dublicateUsers">Дублювати</button>
                        </div>
                    </div>
                    <button id="btnTable" @click="toggleTable">Показати таблицю</button>
                </section>
            </div>

            <footer>
                <div class="footer">
                    <h4 class="footer-text">
                        © 2023 Charity Foundation. All rights reserved
                    </h4>
                    <div class="links">
                        <ul class="contacts">
                            <li>
                                <a class="icon" href=""><i class="fa-solid fa-phone"></i></a>
                            </li>
                            <li>
                                <a class="icon" href=""><i class="fa-solid fa-envelope"></i></a>
                            </li>
                            <li>
                                <a class="icon" href=""><i class="fa-brands fa-telegram"></i></a>
                            </li>
                            <li>
                                <a class="icon" href=""><i class="fa-brands fa-whatsapp"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </footer>
        </div>
    </body>
</template>

<style scoped>
ul {
    margin: 0;
    list-style-type: none;
}

.nav-list {
    background-color: white;
    border-radius: 3px;
}

a {
    text-decoration: none;
    color: inherit;
}

.header {
    display: flex;
    align-items: center;
    background-image: url(https://en.idei.club/uploads/posts/2023-03/1679556803_en-idei-club-p-sino-zhovtii-fon-dizain-pinterest-1.jpg);
    height: 90px;
    justify-content: end;
}

.nav-list {
    display: flex;
    align-items: center;
    margin-right: 20px;
    padding-left: 0;
}

.nav-list li {
    align-items: center;
    color: black;
    font-weight: bold;
    font-size: 19px;
    padding: 0 15px;
}

.nav-list li:last-of-type {
    padding-left: 15px;
    padding-right: 0;

}

.nav-list li a {
    transition: all .25s ease;
}

.nav-list li a:hover {
    color: rgb(0, 142, 24);
}

.nav-list li:last-of-type a {
    display: flex;
    align-items: center;
    color: white;
    background-color: rgba(239, 69, 69, 0.81);
    height: 30px;
    padding: 0 10px;
    border-radius: 3px;
    transition: all .25s ease;
}

.nav-list li:last-of-type a:hover {
    background-color: rgb(111, 29, 29);
}

.nav-list li:last-of-type a svg {
    width: 20px;
    margin-right: 5px;
}

.logo-container {
    flex-grow: 1;
    margin-left: 30px;
}


.logo-img {
    height: 70px;
    width: 70px;
    transition: all .20s ease;
}

.logo-img:hover {
    transform: scale(1.05);
}

.bars {
    display: none;
}

@media (max-width: 750px) {
    nav .bars {
        display: block;
        margin-right: 30px;
        font-size: 30px;
    }

    nav .nav-list {
        display: none;
    }

    nav:hover .nav-list {
        display: block;
        margin-top: 100px;
        margin-right: 19px;
        background-color: white;
        position: relative;
        padding: 10px 10px 10px 0;
    }
}

.content {
    max-width: 100%;
    margin: 0 20px 0 20px;
    border-radius: 20px;
    color: white;
}


section {
    border-radius: 10px;
    margin-bottom: 20px;
    padding: 20px;
    background-color: rgb(0 0 0 / 70%);
    border: 2px solid gray;
}

section p {
    font-size: 20px;
}

.form {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}


input {
    font-size: 25px;
    border-radius: 5px;
    border: 2px solid grey;
    width: 100%;
}

.form label,
span {
    font-size: 20px;
}

select {
    font-size: 25px;
    border-radius: 5px;
    border: 2px solid grey;
    width: 100%;
}

.form h2 {
    margin-top: 0;
    margin-bottom: 20px;
    font-size: 30px;
}

.form h2,
button {
    display: flex;
    justify-content: center;
}

.form button {
    font-size: 25px;
    background-color: royalblue;
    color: white;
    border: 2px solid grey;
    border-radius: 5px;
    width: 100%;
    transition: all .25s ease;
}

.form button:hover {
    background-color: rgb(34, 82, 227);
}

.enterGender {
    display: flex;
    justify-content: space-around;
}

.enterGender input {
    width: auto;
}

.error {
    position: absolute;
    color: red;
    font-size: 17px;
}

.error.button {
    display: block;
    position: static;
    text-align: center;
    color: red;
    font-size: 17px;
}

#btnTable {
    margin-top: 10px;
    padding: 0.5em 0.4em;
    font-size: 14px;
    background-color: rgb(81, 128, 56);
    color: white;
    border: 2px solid grey;
    border-radius: 5px;
    transition: all .25s ease;
}


div.table {
    margin: auto;
    margin-top: 20px;
    overflow-x: auto;
    width: 90%;
}

table,
tr,
th,
td {
    border: 2px solid grey;
    border-collapse: collapse;
}

table {
    margin: auto;
    min-width: 88%;
    text-align: center;
    background-color: white;
    color: black;
}

th,
td {
    padding: 0.5em;
}

tbody tr:hover {
    background-color: rgb(215, 215, 253);
}

tbody tr.selected {
    background-color: rgb(154, 154, 252);
}

.table button {
    color: white;
    border: 2px solid grey;
    border-radius: 5px;
    padding: 0.5em;
    margin: 4px;
    transition: 0.2s;
}

#tableButtons {
    position: sticky;
    left: 0;
}

#btnDelete {
    background-color: rgb(213, 69, 69);
}

#btnDubble {
    background-color: rgb(95, 90, 246);
}

#btnDelete:hover {
    background-color: rgb(167, 55, 55);
}

#btnDubble:hover {
    background-color: rgb(70, 66, 183);
}

.footer {
    background-color: #c0d9f3;
    justify-content: center;
    position: sticky;
    left: 0;
    bottom: 0;
    width: 100%;
}

.footer-text {
    display: flex;
    justify-content: center;
    padding-top: 15px;
    margin: 0;
}


.links {
    display: flex;
    justify-content: center;
}

.contacts {
    display: flex;
    align-items: center;
    padding: 15px 0;
}

.contacts a {
    transition: all .25s ease;
}

.contacts a:hover {
    color: rgb(75, 75, 217);
}

.contacts li {
    padding: 0 20px;
    font-size: 35px;
}

.contacts li:hover {
    font-weight: 800;
}
</style>