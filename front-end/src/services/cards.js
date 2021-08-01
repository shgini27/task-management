import axios from 'axios'
import errorParser from '@/utils/error-parser'

export default {
  /**
   * Method to add new card
   * @param detail
   * @returns {Promise<unknown>}
   */
  add (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/cards', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * Method to change positions of a cards
   * @param positionChanges
   * @returns {Promise<unknown>}
   */
  changePosition (positionChanges) {
    return new Promise((resolve, reject) => {
      axios.post('/cards/positions', positionChanges).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * Method to get card
   * @param cardId
   * @returns {Promise<unknown>}
   */
  getCard (cardId) {
    return new Promise((resolve, reject) => {
      axios.get('/cards/' + cardId).then(({ data }) => {
        resolve(data)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * Method to change card title
   * @param cardId
   * @param title
   * @returns {Promise<unknown>}
   */
  changeCardTitle (cardId, title) {
    return new Promise((resolve, reject) => {
      axios.put('/cards/' + cardId + '/title', { title }).then(({ data }) => {
        resolve(data)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * Method to change card description
   * @param cardId
   * @param description
   * @returns {Promise<unknown>}
   */
  changeCardDescription (cardId, description) {
    return new Promise((resolve, reject) => {
      axios.put('/cards/' + cardId + '/description', { description }).then(({ data }) => {
        resolve(data)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * Method to add comment to the selected card
   * @param cardId
   * @param comment
   * @returns {Promise<unknown>}
   */
  addCardComment (cardId, comment) {
    return new Promise((resolve, reject) => {
      axios.post('/cards/' + cardId + '/comments', { comment }).then(({ data }) => {
        resolve(data)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * Method to fetch all the card activities
   * @param cardId
   * @returns {Promise<unknown>}
   */
  getCardActivities (cardId) {
    return new Promise((resolve, reject) => {
      axios.get('/cards/' + cardId + '/activities').then(({ data }) => {
        resolve(data)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  },

  /**
   * Method to fetch all the attachments of the selected card
   * @param cardId
   * @returns {Promise<unknown>}
   */
  getCardAttachments (cardId) {
    return new Promise((resolve, reject) => {
      axios.get('/cards/' + cardId + '/attachments').then(({ data }) => {
        resolve(data)
      }).catch(error => {
        reject(errorParser.parse(error))
      })
    })
  }
}
