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
  <div class="add-btn-container">
    <button @click="prepareEdit({})" class="add-btn">Add Sponsor</button>
  </div>
  <div class="sponsor">
    <ul class="sponsor-list">
      <li v-for="s in sponsor" :key="s.id_sponsor" class="sponsor-item">
        <div class="sponsor-info-container">
          <a :href="s.imgURL" target="_blank">
            <img class="sponsor-img" :src="s.imgURL" alt="sponsor image">
          </a>
          <div class="sponsor-info">
            <div class="sponsor-name">
              <strong>{{ s.sponsor_name }}</strong>
            </div>
            <div class="sponsor-description">{{ s.sponsor_description }}</div>
          </div>
          <div class="sponsor-actions">
            <button @click="deleteSponsor(s.id_sponsor)" class="delete-btn">Delete</button>
            <button @click="prepareEdit(s)" class="edit-btn">Update</button>
          </div>
        </div>
      </li>
    </ul>

    <div v-if="dialogVisible" class="dialog">
      <h2>{{ isEditing ? 'Edit Sponsor' : 'Add New Sponsor' }}</h2>
      <form @submit.prevent="isEditing ? updateSponsor() : addSponsor()">
        <div class="form-field">
          <label for="name">Назва фонду/спонсора:</label>
          <input v-model="formData.sponsor_name" type="text" id="name" required @input="validateName">
          <span class="error" v-html="errors.nameError"></span>
        </div>
        <div class="form-field">
          <label for="description">Опис спонсора:</label>
          <input v-model="formData.sponsor_description" type="text" id="description" required
                 @input="validateDescription">
          <span class="error" v-html="errors.descriptionError"></span>
        </div>
        <div class="form-field">
          <label for="imgURL">URL картинки:</label>
          <input v-model="formData.imgURL" type="text" id="imgURL" required @input="validateURL">
          <span class="error" v-html="errors.urlError"></span>
        </div>
        <button type="submit">{{ isEditing ? 'Save' : 'Add' }}</button>
        <button type="button" @click="closeDialog">Cancel</button>
        <span class="error button" v-if="errors.hasErrors">{{ errors.buttonError }}</span>
      </form>

    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      sponsor: [],
      dialogVisible: false,
      isEditing: false,
      hasErrors: false,
      formData: {
        id_sponsor: null,
        sponsor_name: '',
        sponsor_description: '',
        imgURL: '',
      },
      errors: {
        nameError: '',
        descriptionError: '',
        buttonError: '',
        urlError: '',
      },
    };
  },

  beforeMount() {
    this.getSponsor();
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
      this.formData.id_sponsor = null;
      this.formData.sponsor_name = '';
      this.formData.sponsor_description = '';
      this.formData.imgURL = '';
    },

    getSponsor() {
      fetch('http://localhost:8080/sponsor')
          .then(res => res.json())
          .then(data => {
            console.log(data);
            this.sponsor = data.sort((a, b) => a.id_sponsor - b.id_sponsor);
          });
    },
    updateSponsor() {
      const isFormValid = this.validateForm();
      if (isFormValid) {
        console.log('Updating sponsor:', this.formData);
        const apiUrl = `http://localhost:8080/sponsor/${this.formData.id_sponsor}`;
        fetch(apiUrl, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.formData),
        })
            .then(res => res.json())
            .then(data => {
              console.log('Update sponsor response:', data);
              this.getSponsor();
              this.closeDialog();
            });
      }
    },

    deleteSponsor(id) {
      fetch(`http://localhost:8080/sponsor/${id}`, {
        method: 'DELETE',
      })
          .then(data => {
            console.log(data);
            this.getSponsor();
          });
    },

    prepareEdit(sponsor) {
      this.isEditing = !!sponsor.id_sponsor;
      this.dialogVisible = true;
      this.formData.id_sponsor = sponsor.id_sponsor || null;
      this.formData.sponsor_name = sponsor.sponsor_name || '';
      this.formData.sponsor_description = sponsor.sponsor_description || '';
      this.formData.imgURL = sponsor.imgURL || '';
    },

    addSponsor() {
      const isFormValid = this.validateForm();
      if (isFormValid) {
        console.log('Adding sponsor:', this.formData);
        fetch('http://localhost:8080/sponsor/add', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.formData),
        })
            .then(data => {
              console.log('Add sponsor response:', data);
              this.getSponsor();
              this.closeDialog();
            });
      }
    },

    validateName() {
      if (this.formData.sponsor_name.length === 0) {
        this.errors.nameError = "Назва обов'язкова";
        return false;
      }
      this.errors.nameError = "✅";
      return true;
    },

    validateDescription() {
      if (this.formData.sponsor_description.length === 0) {
        this.errors.descriptionError = "Опис обов'язковий";
        return false;
      }
      this.errors.descriptionError = "✅";
      return true;
    },

    validateURL() {
      this.errors.nameError = '';
      const urlPattern = /^(https?):\/\/[^\s/$.?#].\S*$/;
      if (this.formData.imgURL.length === 0) {
        this.errors.urlError = "Посилання обов'язкове";
        return false
      } else if (!urlPattern.test(this.formData.imgURL)) {
        this.errors.urlError = "Неправильно введені дані";
        return false
      }
      this.errors.urlError = "✅";
      return true
    },

    validateForm() {
      const isNameValid = this.validateName();
      const isDescriptionValid = this.validateDescription();
      const isUrlValid = this.validateURL();
      if (
          !(
              isNameValid &&
              isDescriptionValid &&
              isUrlValid
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

.add-btn-container {
  display: flex;
  justify-content: center;
  margin-top: 10px; /* Adjust the margin as needed */
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


.sponsor {
  margin: 15px;
  border-radius: 10px;
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
  background-color: #1129c5;
  color: white;
  border: none;
  padding: 8px 16px;
  margin: 4px;
  cursor: pointer;
  border-radius: 4px;
}

.add-btn:hover {
  background-color: #475f93;
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
.sponsor{
  margin-right: 40px;
}

.sponsor-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.sponsor-info {
  margin-left: 10px;
}

.sponsor-description {
  margin-top: 5px;
}

.sponsor-actions {
  margin-top: 10px;
}

.sponsor-item {
  border: 1px solid #ddd;
  border-radius: 5px;
  padding: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 400px;
  margin: 5px auto;
  width: 48%;
  flex-direction: column;
  background-color: rgba(255, 255, 255, 0.51);
}

@media screen and (max-width: 600px) {
  .sponsor-item {
    width: 100%;
  }
}
.sponsor-info-container {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.sponsor-info-container a {
  order: -1;
  margin-bottom: 8px;
}

.sponsor-img {
  width: 100%;
  height: auto;
  border-radius: 5px;
  margin-bottom: 8px;
}
</style>