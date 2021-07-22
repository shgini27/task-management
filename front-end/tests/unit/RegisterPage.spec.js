import { shallowMount, createLocalVue } from '@vue/test-utils'
import VueRouter from 'vue-router'
import RegisterPage from '@/views/RegisterPage'

// Adding vue router to test that
// we can access vm.$router
const localVue = createLocalVue()
localVue.use(VueRouter)

// We need router to check if we
// can redirect to login page
const router = new VueRouter()

// Mock dependency registrationService
jest.mock('@/services/registration')

describe('RegisterPage.vue', () => {
  let wrapper
  let fieldUsername
  let fieldEmail
  let fieldPassword
  let submitButton

  beforeEach(() => {
    wrapper = shallowMount(RegisterPage, {
      localVue,
      router
    })

    fieldUsername = wrapper.find('#username')
    fieldEmail = wrapper.find('#emailAddress')
    fieldPassword = wrapper.find('#password')
    submitButton = wrapper.find('form button[type="submit"]')
  })

  afterAll(() => {
    jest.resetAllMocks()
  })

  it('should render registration form', () => {
    expect(wrapper.find('.logo').attributes().src)
      .toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text())
      .toEqual('Task Management')
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldEmail.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(submitButton.text())
      .toEqual('Create Account')
  })

  it('should contain data model with initial values', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  it('should have form input bound with data model', async () => {
    const username = 'tigr'
    const email = 'tigr@ttweb.org'
    const password = 'Tigr@1996'

    await wrapper.setData({
      form: {
        username: username,
        emailAddress: email,
        password: password
      }
    })

    expect(fieldUsername.element.value).toEqual(username)
    expect(fieldEmail.element.value).toEqual(email)
    expect(fieldPassword.element.value).toEqual(password)
  })

  it('should have submit form event handler `submitForm`', () => {
    const stub = jest.fn()
    wrapper.setMethods({ submitForm: stub })
    submitButton.trigger('submit')
    expect(stub).toBeCalled()
  })

  it('should register wht it is a new User', async () => {
    const stub = jest.fn()

    wrapper.vm.$router.push = stub
    wrapper.vm.form.username = 'tigr'
    wrapper.vm.form.emailAddress = 'tigr@ttweb.org'
    wrapper.vm.form.password = 'Tigr@1996'
    wrapper.vm.submitForm()
    await wrapper.vm.$nextTick(() => {
      expect(stub).toHaveBeenCalledWith({ name: 'LoginPage' })
    })
  })

  it('should fail if it is not a new User', async () => {
    wrapper.vm.form.emailAddress = 'bulat@ttweb.org'
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    wrapper.vm.submitForm()
    await wrapper.vm.$nextTick(null, () => {
      expect(wrapper.find('.failed').isVisible()).toBe(true)
    })
  })
})
