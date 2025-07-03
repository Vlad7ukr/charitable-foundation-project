<template>
  <div class="header">
    <div class="logo-container">
      <img class="logo-img" src="https://i.obozrevatel.com/gallery/2020/2/17/1024px-alexkkievanrus-svg.png" alt="logo">
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
  <div class="donors">
    <table class="crud-table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Name</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="d in donor" :key="d.id_donor">
        <td>{{ d.id_donor }}</td>
        <td>{{ d.contact_email }}</td>
        <td>{{ d.contact_phone }}</td>
        <td>{{ d.donor_name }}</td>
        <td>
          <button @click="deleteDonor(d.id_donor)" class="delete-btn">Delete</button>
          <button @click="prepareEdit(d)" class="edit-btn">Update</button>
        </td>
      </tr>
      </tbody>

    </table>
    <div>
      <button @click="prepareEdit({})" class="add-btn">Add Item</button>
    </div>


    <div v-if="dialogVisible" class="dialog">
      <h2>{{ isEditing ? 'Edit Item' : 'Add New Item' }}</h2>
      <form @submit.prevent="isEditing ? updateDonor() : addDonor()">
        <div class="form-field">
          <label for="name">Name:</label>
          <input v-model="formData.donor_name" type="text" id="name" required @input="validateName">
          <span class="error" v-html="errors.nameError"></span>
        </div>

        <div class="form-field">
          <label for="email">Email:</label>
          <input v-model="formData.contact_email" type="email" id="email" required @input="validateEmail">
          <span class="error" id="email-error"> {{ errors.emailError }}</span>
        </div>

        <div class="form-field">
          <label for="phone">Phone:</label>
          <input v-model="formData.contact_phone" v-mask="'+38(0##) ###-##-##'" type="text" id="phone" required @input="validatePhone">
          <span class="error" v-html="errors.phoneError"></span>
        </div>

        <button type="submit">{{ isEditing ? 'Save' : 'Add' }}</button>
        <button type="button" @click="closeDialog">Cancel</button>
        <span class="error button" v-if="errors.hasErrors">{{ errors.buttonError }}</span>
      </form>

    </div>
  </div>
</template>

<script>

import {mask} from 'vue-the-mask';

export default {
  directives: {
    mask,
  },
  name: 'Donors',
  data() {
    return {
      donor: [],
      dialogVisible: false,
      isEditing: false,
      hasErrors: false,
      formData: {
        id_donor: null,
        name: '',
        email: '',
        phone: '',
      },
      errors: {
        emailError: '',
        nameError: '',
        phoneError: '',
        buttonError: '',
      },
    };
  },

  beforeMount() {
    this.getDonor();
  },
  methods: {
    openDialog() {
      this.isEditing = false;
      this.dialogVisible = true;
    },
    closeDialog() {
      this.dialogVisible = false;
      this.resetForm();
    },

    resetForm() {
      this.formData.id_donor = null;
      this.formData.name = '';
      this.formData.email = '';
      this.formData.phone = '';
    },

    getDonor() {
      fetch('http://localhost:8080/donor')
          .then(res => res.json())
          .then(data => {
            console.log(data);
            this.donor = data.sort((a, b) => a.id_donor - b.id_donor);
          });
    },
    deleteDonor(id) {
      fetch(`http://localhost:8080/donor/${id}`, {
        method: 'DELETE',
      })
          .then(data => {
            console.log(data);
            this.getDonor();
          });
    },

    prepareEdit(donor) {
      this.isEditing = !!donor.id_donor;
      this.dialogVisible = true;
      this.formData.id_donor = donor.id_donor || null;
      this.formData.donor_name = donor.donor_name || '';
      this.formData.contact_email = donor.contact_email || '';
      this.formData.contact_phone = donor.contact_phone || '';
    },

    addDonor() {
      const isFormValid = this.validateForm();
      if (isFormValid) {
        console.log('Adding donor:', this.formData);
        fetch('http://localhost:8080/donor/add', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.formData)
        })
            .then(data => {
              console.log('Add donor response:', data);
              this.getDonor();
              this.closeDialog();
            });
      }
    },

    updateDonor() {
      const isFormValid = this.validateForm();
      if (isFormValid) {
        console.log('Updating donor:', this.formData);
        const apiUrl = `http://localhost:8080/donor/${this.formData.id_donor}`;
        fetch(apiUrl, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.formData),
        })
            .then(res => res.json())
            .then(data => {
              console.log('Update donor response:', data);
              this.getDonor();
              this.closeDialog();
            });
      }
    },
    validateEmail() {
      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

      if (this.formData.contact_email.length === 0) {
        this.errors.emailError = "Електронна пошта обов'язкова";
        return false
      }
      if (!emailPattern.test(this.formData.contact_email)) {
        this.errors.emailError = "Неправильно введені дані";
        return false
      }
      this.errors.emailError = "✅";
      return true
    },

    validateName() {
      this.errors.nameError = '';
      const namePattern = /^[A-ZА-ЯЇІ][a-zа-яїі'-]+$/;

      if (this.formData.donor_name.length === 0) {
        this.errors.nameError = "Ім'я обов'язкове";
        return false
      } else if (!namePattern.test(this.formData.donor_name)) {
        this.errors.nameError = "Неправильно введені дані";
        return false
      }

      this.errors.nameError = "✅";
      return true

    },
    validatePhone() {

      this.errors.phoneError = '';


      const phonePattern = /^\+38\(0[1-9]\d{1}\) \d{3}-\d{2}-\d{2}$/;

      if (this.formData.contact_phone.length === 0) {
        this.errors.phoneError = 'Номер телефону обов\'язковий';
        return false
      } else if (!phonePattern.test(this.formData.contact_phone)) {
        this.errors.phoneError = 'Неправильно введені дані';
        return false
      }
        this.errors.phoneError = "✅"
        return true
    },

    validateForm() {
      const isEmailValid = this.validateEmail();
      const isNameValid = this.validateName();
      const isPhoneValid = this.validatePhone();
      if (
          !(
              isEmailValid &&
              isNameValid &&
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
  },
};
</script>

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
  height: 80px;
  justify-content: end;
  position: sticky;
  top: 0;
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

@media (max-width: 755px) {
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


.donors {
  margin: 15px;
  border-radius: 10px;
}

.crud-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
  background: rgba(255, 255, 255, 0.32);
}

.crud-table th,
.crud-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.crud-table th {
  background-color: #f2f2f2;
}

.edit-btn {
  width: 10vh;
  color: white;
  background-color: blueviolet;
  font-weight: bold;
  border: none;
  padding: 8px 16px;
  margin: 4px;
  cursor: pointer;
  border-radius: 4px;
}

.edit-btn:hover {
  background-color: rgb(91, 0, 175);
}

.delete-btn {
  color: white;
  font-weight: bold;
  background-color: darkred;
  width: 10vh;
  border: none;
  padding: 8px 16px;
  margin: 4px;
  cursor: pointer;
  border-radius: 4px;
}

.delete-btn:hover {
  background-color: rgb(99, 0, 0);
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