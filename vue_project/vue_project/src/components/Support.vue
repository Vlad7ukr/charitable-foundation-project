<template>
  <body>
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
            <svg id="heart-img" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="heart" role="img"
                 xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"
                 class="fs-s mr-10 svg-inline--fa fa-heart fa-w-16 heart" data-v-4180a957="">
              <path fill="currentColor"
                    d="M462.3 62.6C407.5 15.9 326 24.3 275.7 76.2L256 96.5l-19.7-20.3C186.1 24.3 104.5 15.9 49.7 62.6c-62.8 53.6-66.1 149.8-9.9 207.9l193.5 199.8c12.5 12.9 32.8 12.9 45.3 0l193.5-199.8c56.3-58.1 53-154.3-9.8-207.9z"
                    data-v-4180a957="" class=""></path>
            </svg>
            Активні збори

          </router-link>
        </li>
      </ul>
    </nav>
  </div>

  <div class="content">
    <section class="main">
      <div>
        <button @click="prepareEdit({})" class="add-btn">Add Item</button>
      </div>
      <table>
        <thead>
        <tr>
          <th>ID</th>
          <th>Тип допомоги</th>
          <th>Ціль</th>
          <th>Початок збору</th>
          <th>Дії</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="e in event" :key="e.id_event">
          <td>{{ e.id_event }}</td>
          <td>{{ e.description }}</td>
          <td>{{ e.target }}</td>
          <td>{{ e.start_date }}</td>
          <td>
            <button class="view"><i class="fa-solid fa-eye"></i></button>
            <button @click="prepareEdit(e)" class="edit"><i class="fa-solid fa-pen-to-square" style="color: #ff00c8;"></i></button>
            <button @click="deleteEvent(e.id_event)" class="delete"><i class="fa-solid fa-trash" style="color: #f50000;"></i></button>
          </td>
        </tr>
        </tbody>
      </table>
    </section>
  </div>

  <div v-if="dialogVisible" class="dialog">
    <h2>{{ isEditing ? 'Edit Event' : 'Add New Event' }}</h2>
    <form @submit.prevent="addEvent()">

      <div class="form-field">
        <label for="description">Тип допомоги:</label>
        <input v-model="formData.description" type="text" id="description" required @input="validateDescription">
        <span class="error" v-html="errors.descriptionError"></span>
      </div>
      <div class="form-field">
        <label for="target">Ціль(₴):</label>
        <input v-model="formData.target" type="number" id="target" min="0.1" step="0.1" required @input="validateTarget">
        <span class="error" id="target-error"> {{ errors.targetError }}</span>
      </div>
      <div class="form-field">
        <label for="date">Початок збору:</label>
        <input v-model="formData.start_date" type="date" id="date" required @input="validateDate" :max="getTodayDate()">
        <span class="error" id="date-error"> {{ errors.dateError }}</span>
      </div>
      <button type="submit">{{ isEditing ? 'Save' : 'Add' }}</button>
      <button type="button" @click="closeDialog">Cancel</button>
      <span class="error button" v-if="errors.hasErrors">{{ errors.buttonError }}</span>
    </form>
  </div>
  </body>

</template>

<script>
export default {
  data() {
    return {
      event: [],
      dialogVisible: false,
      isEditing: false,
      hasErrors: false,
      formData: {
        id_event: null,
        description: '',
        target: '',
        start_date: '',
      },
      errors: {
        descriptionError: '',
        targetError: '',
        dateError: '',
        buttonError: '',
      },
    };
  },

  beforeMount() {
    this.getEvent();
  },
  methods: {
    getTodayDate() {
      const today = new Date()
      const year = today.getFullYear()
      let month = today.getMonth() + 1
      let day = today.getDate()
      month = month < 10 ? `0${month}` : month
      day = day < 10 ? `0${day}` : day
      return `${year}-${month}-${day}`
    },

    openDialog() {
      this.isEditing = false;
      this.dialogVisible = true;
    },
    closeDialog() {
      this.dialogVisible = false;
      this.resetForm();
    },

    resetForm() {
      this.formData.id_event = null;
      this.formData.description = '';
      this.formData.target = '';
      this.formData.start_date = '';
    },

    getEvent() {
      fetch('http://localhost:8080/support')
          .then(res => res.json())
          .then(data => {
            console.log(data);
            this.event = data.sort((a, b) => a.id_event - b.id_event);
          });
    },

    deleteEvent(id) {
      fetch(`http://localhost:8080/support/${id}`, {
        method: 'DELETE',
      })
          .then(data => {
            console.log(data);
            this.getEvent();
          });
    },

    prepareEdit(event) {
      this.isEditing = !!event.id_event;
      this.dialogVisible = true;
      this.formData.id_event = event.id_event || null;
      this.formData.description = event.description || '';
      this.formData.target = event.target || '';
      this.formData.start_date = event.start_date || '';
    },

    addEvent() {
      const isFormValid = this.validateForm();
      if (isFormValid) {
        console.log('Adding event:', this.formData);
        fetch('http://localhost:8080/support/add', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.formData),
        })
            .then(data => {
              console.log('Add event response:', data);
              this.getEvent();
              this.closeDialog();
            });
      }
    },

    validateDescription() {
      if (this.formData.description.length === 0) {
        this.errors.descriptionError = "Тип допомоги обов'язковий";
        return false;
      }
      this.errors.descriptionError = "✅";
      return true;
    },

    validateTarget() {
      const targetPattern = /^\d+(\.\d{1,2})?$/;

      if (this.formData.target.length === 0) {
        this.errors.targetError = "Ціль обов'язкова";
        return false;
      }
      if (!targetPattern.test(this.formData.target)) {
        this.errors.targetError = "Неправильно введені дані";
        return false;
      }
      this.errors.targetError = "✅";
      return true;
    },

    validateDate() {
      this.errors.dateError = '';
      if (this.formData.start_date.length === 0) {
        this.errors.dateError = "Дата обов'язкова";
        return false;
      } else {
        const startDate = new Date(this.formData.start_date);
        const currentDate = new Date();
        if (startDate > currentDate) {
          this.errors.dateError = "Неправильна дата (майбутня)";
          return false;
        }
      }
      this.errors.dateError = "✅";
      return true;
    },

    validateForm() {
      const isDescriptionValid = this.validateDescription();
      const isTargetValid = this.validateTarget();
      const isDateValid = this.validateDate();
      if (
          !(
              isDescriptionValid &&
              isTargetValid &&
              isDateValid
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
  },
};
</script>

<style>
ul {
  margin: 0;
  list-style-type: none;
}

.nav-list-support {
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

.nav-list-support {
  display: flex;
  align-items: center;
  margin-right: 20px;
  padding-left: 0;
}

.nav-list-support li {
  align-items: center;
  color: black;
  font-weight: bold;
  font-size: 19px;
  padding: 0 15px;
}

.nav-list-support li:last-of-type {
  padding-left: 15px;
  padding-right: 15px;

}

.nav-list-support li a {
  transition: all .25s ease;
}

.nav-list-support li a:hover {
  color: rgb(0, 142, 24);
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

  nav .nav-list-support {
    display: none;
  }

  nav:hover .nav-list-support {
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
}


section {
  border-radius: 10px;
  margin-bottom: 20px;
  padding: 20px;
  background-color: rgb(0 0 0 / 70%);
  border: 2px solid gray;
  color: white;
}

.title {
  font-size: 45px;
  font-weight: bold;
  background-color: rgb(0 0 0 / 70%);
}

@media (max-width: 410px) {
  .title {
    font-size: 30px;
  }

}

table {
  width: 100%;
  border-collapse: collapse;
}

table, th, td {
  border: 1px solid white;
}

th, td {
  padding: 10px;
  text-align: center;
}

td {
  width: 25%;
}

button i {
  font-size: 20px;
}

@media (max-width: 732px) {
  button i {
    margin: 5px;
  }
}

button {
  padding: 5px 10px;
  margin: 5px;
}

@media (max-width: 428px) {
  td, th {
    padding: 2px;
  }
}

@media (max-width: 732px) {
  .main {
    padding: 0;
  }
}


.title h1 {
  margin: 15px 0;
}

section p {
  font-size: 20px;

}

section a {
  font-size: 18px;
  font-weight: bold;
  padding-left: 10px;
  height: 37px;
  display: flex;
  color: white;
  background-color: rgb(112 82 82 / 81%);
  align-items: center;
  justify-content: start;
  border-radius: 10px;
  width: fit-content;
  padding: 0 20px;
  transition: all .20s ease;

}

section a:hover {
  background-color: cadetblue;
}

.section a svg {
  justify-content: end;
}

@media (max-width: 438px) {
  section a {
    height: 45px;
  }

}

.about {
  margin-bottom: 0;
}

.footer {
  background-color: #c0d9f3;
  justify-content: center;
  margin-top: 20px;
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
}

@media (max-height: 722px) {
  .footer {
    position: static;
  }

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

.contacts li {
  padding: 0 20px;
  font-size: 35px;
}

.contacts li:hover {
  font-weight: 800;
}

.add-btn {
  font-weight: bold;
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 8px 16px;
  margin: 4px;
  cursor: pointer;
  border-radius: 4px;
}

.add-btn:hover {
  background-color: #45a049;
}

.dialog {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.dialog form {
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding-right: 40px;

}

.dialog label {
  display: block;
  margin-bottom: 8px;
}

.dialog input {
  width: 100%;
  padding: 8px;
  margin-bottom: 16px;
}

.dialog .error-message {
  color: red;
  margin-bottom: 16px;
}

.dialog button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 8px 16px;
  cursor: pointer;
  border-radius: 4px;
  margin-right: 8px;
}

.dialog button:last-child {
  margin-right: 0;
  background-color: #ccc;
}

.dialog h2 {
  color: rgb(255, 255, 255);
  margin-right: 10px;
  font-size: 30px;
}

@media screen and (max-width: 600px) {
  .crud-table {
    border: 0;
  }

  .crud-table thead {
    display: none;
  }

  .crud-table tr {
    margin-bottom: 10px;
    display: block;
    border-bottom: 2px solid #ddd;
  }

  .crud-table td {
    display: block;
    text-align: center;
    font-size: 13px;
    border-bottom: 1px dotted #ccc;
    border-right: 1px solid transparent;
    width: 95%;
  }

  .crud-table td:last-child {
    border-bottom: 0;
  }

  .crud-table td:before {
    content: attr(data-label);
    float: left;
    text-transform: uppercase;
    font-weight: bold;
  }

  .dialog form {
    margin: 20px;
  }

  h2 {
    margin: 20px;
  }
}
.error {
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
</style>