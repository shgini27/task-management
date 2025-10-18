import Vue from 'vue'
import VueI18n from 'vue-i18n'
import { enUS, ru, tkTK } from './locale'

Vue.use(VueI18n)

export const i18n = new VueI18n({
  locale: 'en_US',
  messages: {
    en_US: enUS,
    ru_RU: ru,
    tk_TK: tkTK
  }
})
