import { createRouter, createWebHistory } from 'vue-router'
import DefaultLayout from '@/layouts/default/Index'
import AdminLayout from '@/layouts/admin/Index'
import AuthenticationLayout from '@/layouts/authentication/Index'
import MainView from '@/modules/MainView'
import UserView from '@/modules/users/views/UserView'
import BoardView from '@/modules/boards/views/BoardView'
import Bottle from '@/modules/bottles/views/Bottle'
import BottleAll from '@/modules/bottles/views/BottleAll'
import BottleDetailView from '@/modules/bottles/views/BottleDetailView'
// import NoticeView from '@/modules/notices/views/NoticeView'
import FaqView from '@/modules/faqs/views/FaqView'
import AdminBottleView from '@/layouts/admin/AdminBottleView'
import AdminAddBottleView from '@/layouts/admin/AdminAddBottleView'
import AdminBottleDetailView from '@/layouts/admin/AdminBottleDetailView'
import AdminBottle from '@/layouts/admin/AdminBottle'
import AdminTagView from '@/layouts/admin/AdminTagView'
import AdminNoticeCreateComp from '@/modules/notices/components/AdminNoticeCreateComp'
import AdminNoticeDetailComp from '@/modules/notices/components/AdminNoticeDetailComp'
import AdminNoticeEditComp from '@/modules/notices/components/AdminNoticeEditComp'
import AdminNoticeListComp from '@/modules/notices/components/AdminNoticeListComp'
import NoticeListComp from '@/modules/notices/components/NoticeListComp'
import NoticeDetailComp from '@/modules/notices/components/NoticeDetailComp'

const routes = [
  {
    path: '/',
    name: 'DefaultLayout',
    component: DefaultLayout,
    children: [
      {
        path: '/',
        name: 'MainView',
        component: MainView,
        props: true
      },
      {
        path: '/users',
        name: 'UserView',
        component: UserView,
        children: [
          {
            path: '/users/mypage',
            name: 'UserMyPageComp',
            component: () => import('@/modules/users/components/UserMyPageComp'),
            meta: { title: 'UserMyPageComp' }
          },
          {
            path: '/users/addition',
            name: 'UserRegisterComp',
            component: () => import('@/modules/users/components/UserRegisterComp'),
            meta: { title: 'UserRegisterComp' }
          }, {
            path: '/users/tags/addition',
            name: 'TagRegisterComp',
            component: () => import('@/modules/users/components/TagRegisterComp')
          }
        ]
      },
      {
        path: '/bottles',
        name: 'Bottle',
        component: Bottle,
        props: true,
        children: [
          {
            path: '/bottles',
            name: 'BottleAll',
            component: BottleAll,
            props: true
          },
          {
            path: '/bottles/:bottleNo',
            name: 'BottleDetailView',
            component: BottleDetailView,
            props: true
          }
        ]
      },
      {
        path: '/boards',
        name: 'BoardView',
        component: BoardView,
        children: [
          {
            path: '/boards',
            name: 'BoardListComp',
            component: () => import('@/modules/boards/components/BoardListComp')
          },
          {
            path: '/boards/:boardNo',
            name: 'BoardDetailComp',
            component: () => import('@/modules/boards/components/BoardDetailComp'),
            props: true
          },
          {
            path: '/boards/addition',
            name: 'BoardCreateComp',
            component: () => import('@/modules/boards/components/BoardCreateComp')
          },
          {
            path: '/boards/set-data',
            name: 'BoardEditComp',
            component: () => import('@/modules/boards/components/BoardEditComp')
          }
        ]
      },
      {
        path: '/notices',
        name: 'NoticeView',
        component: () => import('@/modules/notices/views/NoticeView'),
        children: [
          {
            path: '/notices',
            name: 'NoticeListComp',
            component: NoticeListComp
          },
          {
            path: '/notices/:noticeNo',
            name: 'NoticeDetailComp',
            component: NoticeDetailComp
          }
        ]
      },
      {
        path: '/faqs',
        name: 'FaqView',
        component: FaqView
      }
    ]
  }
  , {
    path: '/admin',
    name: 'AdminLayout',
    component: AdminLayout,
    children: [
      {
        path: '/admin/bottles',
        name: 'AdminBottle',
        component: AdminBottle,
        children: [
          {
            path: '/admin/bottles',
            name: 'AdminBottleView',
            component: AdminBottleView
          },
          {
            path: '/admin/bottles/:bottleNo',
            name: 'AdminBottleDetailView',
            component: AdminBottleDetailView,
            props: true
          },
          {
            path: '/admin/bottle/addition',
            name: 'AdminAddBottleView',
            component: AdminAddBottleView
          },
          {
            path: '/admin/tags',
            name: 'AdminTagView',
            component: AdminTagView
          }
        ]
      },
      {
        path: '/admin/tags',
        name: 'AdminTagView',
        component: AdminTagView
      },
      {
        path: '/admin/users',
        name: 'AdminUserManagement',
        component: () => import('@/modules/users/components/AdminManaging')
      },
      {
        path: '/admin/notices',
        name: 'AdminNoticeView',
        component: () => import('@/modules/notices/views/AdminNoticeView'),
        children: [
          {
            path: '/admin/notices',
            name: 'AdminNoticeListComp',
            component: AdminNoticeListComp
          },
          {
            path: '/admin/notices/:noticeNo',
            name: 'AdminNoticeDetailComp',
            component: AdminNoticeDetailComp
          },
          {
            path: '/admin/notices/addition',
            name: 'AdminNoticeCreateComp',
            component: AdminNoticeCreateComp
          },
          {
            path: '/admin/notices/set-data',
            name: 'AdminNoticeEditComp',
            component: AdminNoticeEditComp
          }
        ]
      }
    ]
  },
  {
    path: '/auth',
    name: 'AuthenticationLayout',
    component: AuthenticationLayout,
    children: [
      {
        path: '/login',
        name: 'UserLoginComp',
        component: () => import('@/modules/users/components/UserLoginComp')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
