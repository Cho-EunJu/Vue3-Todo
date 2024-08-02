import { createApp } from 'vue'
import App from './App.vue'

import { router } from './routers/index'
import { createPinia } from 'pinia'
import { $axios } from './api'

createApp(App)
    .use(router)
    .use(createPinia())
    .use($axios)
    .mount('#app')
