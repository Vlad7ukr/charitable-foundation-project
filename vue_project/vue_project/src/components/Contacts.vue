<script>

</script>

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
              <svg id="heart-img" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="heart" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="fs-s mr-10 svg-inline--fa fa-heart fa-w-16 heart" data-v-4180a957="">
                <path fill="currentColor" d="M462.3 62.6C407.5 15.9 326 24.3 275.7 76.2L256 96.5l-19.7-20.3C186.1 24.3 104.5 15.9 49.7 62.6c-62.8 53.6-66.1 149.8-9.9 207.9l193.5 199.8c12.5 12.9 32.8 12.9 45.3 0l193.5-199.8c56.3-58.1 53-154.3-9.8-207.9z"
                      data-v-4180a957="" class=""></path></svg>
              Активні збори

            </router-link>
          </li>
        </ul>
      </nav>
    </div>

    <div class="content-contacts">
        <section class="title-contacts">
            <h1>Контакти</h1>
            <div class="numbers">
                <p>Номери телефонів:
                    <br><br>
                    0 800 800 800
                    <br>
                    0 900 900 900
                    <br>
                    0 800 100 100
                </p>
            </div>
            <div class="adress">
                <p>
                    Адреси наших офісів:
                    <br><br>
                    м.Київ, вул Українська, будинок 24
                    <br>
                    м.Львів, вул Українська, будинок 24
                    <br>
                    м.Одеса, вул Українська, будинок 24
                    <br>
                    м.Харків, вул Українська, будинок 24
                </p>
            </div>
        </section>
    </div>

</body>

</template>

<style>
ul {
    margin: 0;
    list-style-type: none;
}

.nav-list-contacts {
    background-color:white;
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

.nav-list-contacts {
    display: flex;
    align-items: center;
    margin-right: 20px;
    padding-left: 0;
}

.nav-list-contacts li {
    align-items: center;
    color: black;
    font-weight: bold;
    font-size: 19px;
    padding: 0 15px;
}

.nav-list-contacts li:last-of-type {
    padding-left: 15px;
    padding-right: 0;

}

.nav-list-contacts li a {
    transition: all .25s ease;
}

.nav-list-contacts li a:hover {
    color: rgb(0, 142, 24);
}

.nav-list-contacts li:last-of-type a {
    display: flex;
    align-items: center;
    color: white;
    background-color: rgba(239, 69, 69, 0.81);
    height: 30px;
    padding: 0 10px;
    border-radius: 3px;
    transition: all .25s ease;
}

.nav-list-contacts li:last-of-type a:hover {
    background-color: rgb(111, 29, 29);
}

.nav-list-contacts li:last-of-type a svg {
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

.logo-img:hover{
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

    nav .nav-list-contacts {
        display: none;
    }

    nav:hover .nav-list-contacts {
        display: block;
        margin-top: 100px;
        margin-right: 19px;
        background-color: white;
        position: relative;
        padding: 10px 10px 10px 0;
    }
}

.content-contacts {
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

.title-contacts {
    font-size: 45px;
    font-weight: bold;
    background-color: rgb(0 0 0 / 70%);
}

@media (max-width: 410px) {
    .title-contacts {
        font-size: 30px;
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

.footer-contacts {
    background-color: #c0d9f3;
    justify-content: center;
    margin-top: 20px;
    position: absolute;
    bottom: 0;
    left: 0;
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

.contacts li {
    padding: 0 20px;
    font-size: 35px;
}

.contacts li:hover {
    font-weight: 800;
}
</style>