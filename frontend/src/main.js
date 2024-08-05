import { createApp } from 'vue'
import App from './App.vue'

import { router } from './routers/index'
import { createPinia } from 'pinia'
import { $axios } from './api'

const pinia = createPinia();

createApp(App)
    .use(router)
    .use($axios)
    .use(pinia)
    .mount('#app')
