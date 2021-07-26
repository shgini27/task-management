import { shallowMount, createLocalVue } from '@vue/test-utils'
import Vuelidate from 'vuelidate'
import VueRouter from 'vue-router'
import LoginPage from '@/views/LoginPage.vue'
import authenticationService from '@/services/authentication'

// Setup local Vue with Vuelidate
const localVue = createLocalVue()
localVue.use(Vuelidate)
localVue.use(VueRouter)
const router = new VueRouter()

// Mock dependency registrationService
jest.mock('@/services/authentication')

describe('LoginPage.vue', () => {
  let wrapper
  let fieldUsername
  let fieldPassword
  let submitButton
  let authenticateSpy

  beforeEach(() => {
    wrapper = shallowMount(LoginPage, {
      localVue,
      router
    })
    fieldUsername = wrapper.find('#username')
    fieldPassword = wrapper.find('#password')
    submitButton = wrapper.find('form button[type="submit"]')

    // Create spy for registration service
    authenticateSpy = jest.spyOn(authenticationService, 'authenticate')
  })

  afterEach(() => {
    authenticateSpy.mockReset()
    authenticateSpy.mockRestore()
  })

  afterAll(() => {
    jest.resetAllMocks()
  })

  it('should render login form', () => {
    expect(wrapper.find('.logo').attributes().src).toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text()).toEqual('Task Management')
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(submitButton.text()).toEqual('Sign in')
    expect(wrapper.find('.link-forgot-password')).toBeTruthy()
  })

  it('should contain data model with initial values', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  it('should have form inputs bound with data model', async () => {
    const username = 'tigr'
    const password = 'Tigr@1996'

    await wrapper.setData({
      form: {
        username: username,
        password: password
      }
    })

    expect(fieldUsername.element.value).toEqual(username)
    expect(fieldPassword.element.value).toEqual(password)
  })

  it('should have form submit event handler `submitForm`', () => {
    const stub = jest.fn()
    wrapper.setMethods({ submitButton: stub })
    submitButton.trigger('submit')
    expect(stub).toBeCalled()
  })

  it('should succeed when credentials are valid', async () => {
    expect.assertions(2)
    const stub = jest.fn()
    wrapper.vm.$router.push = stub

    await wrapper.setData({
      form: {
        username: 'tigr',
        password: 'Tigr@1996'
      }
    })

    wrapper.vm.submitForm()
    expect(authenticateSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(stub).toHaveBeenCalledWith({ name: 'home' })
  })

  it('should fail when credentials are invalid', async () => {
    expect.assertions(3)

    // In the mock only password `Tigr@1996` combined with
    // username `tigr` or `tigr@ttweb.org` is valid
    await wrapper.setData({
      form: {
        username: 'bulat',
        password: 'Bulat@1996'
      }
    })

    expect(wrapper.find('.failed').isVisible()).toBe(false)
    wrapper.vm.submitForm()
    expect(authenticateSpy).toBeCalled()
    await wrapper.vm.$nextTick()
    expect(wrapper.find('.failed').isVisible()).toBe(true)
  })

  it('should have validation to prevent invalid data submit', async () => {
    // Empty form
    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toHaveBeenCalled()

    // Only username is valid
    await wrapper.setData({
      form: {
        username: 'tigr'
      }
    })

    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toHaveBeenCalled()

    // Only email is valid
    await wrapper.setData({
      form: {
        username: 'tigr@ttweb.org'
      }
    })

    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toHaveBeenCalled()

    // Only password is valid
    await wrapper.setData({
      form: {
        username: '',
        password: 'Tigr@1986'
      }
    })

    wrapper.vm.submitForm()
    expect(authenticateSpy).not.toHaveBeenCalled()
  })
})
