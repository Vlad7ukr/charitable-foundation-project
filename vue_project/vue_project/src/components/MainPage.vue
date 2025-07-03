<script>

</script>

<template>
    <body>
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
              <svg id="heart-img" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="heart" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="fs-s mr-10 svg-inline--fa fa-heart fa-w-16 heart" data-v-4180a957="">
                <path fill="currentColor" d="M462.3 62.6C407.5 15.9 326 24.3 275.7 76.2L256 96.5l-19.7-20.3C186.1 24.3 104.5 15.9 49.7 62.6c-62.8 53.6-66.1 149.8-9.9 207.9l193.5 199.8c12.5 12.9 32.8 12.9 45.3 0l193.5-199.8c56.3-58.1 53-154.3-9.8-207.9z"
                      data-v-4180a957="" class=""></path></svg>
              Активні збори

            </router-link>
          </li>
        </ul>
      </nav>
    </div>

    <div class="content">
        <section class="main-img">
            <h1>Благодійний фонд</h1>
            <img src="https://vitmark.com/wp-content/uploads/2022/02/MicrosoftTeams-image-30.png" alt="">
        </section>
        <section class="slider">
            <h2>Як подати заявку на отримання допомоги:</h2>
            <p>1. Оформити офіційний запит від підрозділу з переліком потреб 
                (обов’язково з підписом та печаткою командира) та зробити його скан/фото.
            </p>
            <a href="">Зразок оформлення запиту тут
                <svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg" 
                class="base-btn__arrow arrow fs-0"><path d="M9.334 22.67L22.667 9.335M9.334 9.336h13.333v13.333" 
                stroke="#fff" stroke-width="2" stroke-linejoin="round"></path></svg>
            </a>
            <p>
                2. Коректно заповнити форму фонду ( вказати всі необхідні деталі, 
                контакти та прикріпити скан/фото запиту від командування)
            </p>
            <a href="">Заповнити форму тут
                <svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg" 
                class="base-btn__arrow arrow fs-0"><path d="M9.334 22.67L22.667 9.335M9.334 9.336h13.333v13.333" 
                stroke="#fff" stroke-width="2" stroke-linejoin="round"></path></svg>
            </a>
        </section>
        <section class="news">
            <h2>Про команду</h2>
            <p>Понад 200 волонтерів та робітників фонду працюють щодня, щоб кожна ваша гривня 
                перетворювалась на цінну допомогу Україні. 
                У кожного з нас своя історія приєднання до команди. Волонтери з досвідом, громадські активісти, 
                спеціалісти у різних галузях – ми всі прийшли у фонд, 
                щоб бути корисними Україні та українцям у вирішальний момент історії нашої країни. 
                Вже багато місяців наша команда щоденно працює на перемогу: надає допомогу 
                українському війську та цивільним на прифронтових та деокупованих територіях.
                Нам довіряють тисячі українців. Це велика честь і велика відповідальність. 
                Ми робимо все можливе, щоб виправдати цю довіру та наблизити нашу країну до перемоги!
            </p>
            <a href="">Детальніше
                <svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg" 
                class="base-btn__arrow arrow fs-0"><path d="M9.334 22.67L22.667 9.335M9.334 9.336h13.333v13.333" 
                stroke="#fff" stroke-width="2" stroke-linejoin="round"></path></svg>
            </a>
        </section>

        <section class="about">
            <h2>Збори</h2>
            <p>Поряд з основними напрямками діяльності фонду, ми реалізовуємо окремі проєкти, 
                які несуть стратегічну цінність для Сил оборони України та для відновлення 
                гідних умов життя цивільних на деокупованих та прифронтових територіях. Долучайтесь!
            </p>
            <a href="">Стати партнером
                <svg width="32" height="32" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg" 
                class="base-btn__arrow arrow fs-0"><path d="M9.334 22.67L22.667 9.335M9.334 9.336h13.333v13.333" 
                stroke="#fff" stroke-width="2" stroke-linejoin="round"></path></svg>
            </a>
        </section>
    </div>
    <div class="lds-heart"><div></div></div>

    <footer>
        <div class="footer-main">
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

</body>

</template>

<style>
body {
    margin: 0;
    font-family: 'Raleway', sans-serif;
    background-image: url(https://en.idei.club/uploads/posts/2023-03/1679556803_en-idei-club-p-sino-zhovtii-fon-dizain-pinterest-1.jpg);
}

ul {
    margin: 0;
    list-style-type: none;
}

.nav-list {
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

.logo-img:hover{
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

.content {
    max-width: 100%;
    margin: 0 20px 0 20px;
    border-radius: 20px;
    color: white;
}

section img {
    width: 100%;
}

.main-img h1 {
    font-size: 80px;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    text-align: center;
    margin: 0;
    color: wheat;
}

@media (max-width:600px) {
    .main-img h1 {
        font-size: 40px;
    }
}

section {
    border-radius: 10px;
    margin-bottom: 20px;
    padding: 20px;
    background-color: rgb(0 0 0 / 70%);
    border: 2px solid gray;
}

section p {
    color: white;
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

.footer-main {
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

.lds-heart {
    display: inline-block;
    position: relative;
    width: 80px;
    height: 80px;
    margin-left: 20px;
    transform: rotate(45deg);
    transform-origin: 40px 40px;
  }
  .lds-heart div {
    top: 32px;
    left: 32px;
    position: absolute;
    width: 32px;
    height: 32px;
    background: #cef;
    animation: lds-heart 1.2s infinite cubic-bezier(0.215, 0.61, 0.355, 1);
  }
  .lds-heart div:after,
  .lds-heart div:before {
    content: " ";
    position: absolute;
    display: block;
    width: 32px;
    height: 32px;
    background: #cef;
  }
  .lds-heart div:before {
    left: -24px;
    border-radius: 50% 0 0 50%;
  }
  .lds-heart div:after {
    top: -24px;
    border-radius: 50% 50% 0 0;
  }
  @keyframes lds-heart {
    0% {
      transform: scale(0.95);
    }
    5% {
      transform: scale(1.1);
    }
    39% {
      transform: scale(0.85);
    }
    45% {
      transform: scale(1);
    }
    60% {
      transform: scale(0.95);
    }
    100% {
      transform: scale(0.9);
    }
  }
</style>