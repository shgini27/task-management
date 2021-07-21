import { shallowMount } from '@vue/test-utils'
import LoginPage from '@/views/LoginPage.vue'

describe('LoginPage.vue', () => {
  it('renders title', () => {
    const msg = 'Login'
    const wrapper = shallowMount(LoginPage, {
      propsData: { msg }
    })
    expect(wrapper.text()).toMatch(msg)
  })
})
