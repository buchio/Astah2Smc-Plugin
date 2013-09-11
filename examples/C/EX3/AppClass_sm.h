/*
 * ex: set ro:
 * DO NOT EDIT.
 * generated by smc (http://smc.sourceforge.net/)
 * from file : AppClass.sm
 */

#ifndef _H_APPCLASS_SM
#define _H_APPCLASS_SM

#include <assert.h>
#define STATEMAP_DEBUG 1
#include <statemap.h>


struct AppClass;
struct AppClassContext;

struct AppClassState
{

    void(*C)(struct AppClassContext*);
    void(*EOS)(struct AppClassContext*);
    void(*Error)(struct AppClassContext*);
    void(*OK)(struct AppClassContext*);
    void(*One)(struct AppClassContext*);
    void(*Unknown)(struct AppClassContext*);
    void(*Zero)(struct AppClassContext*);

    void(*Default)(struct AppClassContext*);
    STATE_MEMBERS
};

extern const struct AppClassState StartMap_PushIt;
extern const struct AppClassState StartMap_PopIt;
extern const struct AppClassState StartMap_Acceptable;
extern const struct AppClassState StartMap_Unacceptable;
extern const struct AppClassState ZerosMap_PushIt;
extern const struct AppClassState ZerosMap_PopIt;
extern const struct AppClassState OnesMap_PushIt;
extern const struct AppClassState OnesMap_PopIt;

struct AppClassContext
{
    struct AppClass *_owner;
    FSM_MEMBERS(AppClass)
};

#define AppClassContext_Init(fsm, owner) \
    FSM_INIT((fsm), &StartMap_PushIt); \
    (fsm)->_owner = (owner);

#define AppClassContext_C(fsm) \
    assert(getState(fsm) != NULL); \
    setTransition((fsm), "C"); \
    getState(fsm)->C(fsm); \
    setTransition((fsm), NULL);

#define AppClassContext_EOS(fsm) \
    assert(getState(fsm) != NULL); \
    setTransition((fsm), "EOS"); \
    getState(fsm)->EOS(fsm); \
    setTransition((fsm), NULL);

#define AppClassContext_Error(fsm) \
    assert(getState(fsm) != NULL); \
    setTransition((fsm), "Error"); \
    getState(fsm)->Error(fsm); \
    setTransition((fsm), NULL);

#define AppClassContext_OK(fsm) \
    assert(getState(fsm) != NULL); \
    setTransition((fsm), "OK"); \
    getState(fsm)->OK(fsm); \
    setTransition((fsm), NULL);

#define AppClassContext_One(fsm) \
    assert(getState(fsm) != NULL); \
    setTransition((fsm), "One"); \
    getState(fsm)->One(fsm); \
    setTransition((fsm), NULL);

#define AppClassContext_Unknown(fsm) \
    assert(getState(fsm) != NULL); \
    setTransition((fsm), "Unknown"); \
    getState(fsm)->Unknown(fsm); \
    setTransition((fsm), NULL);

#define AppClassContext_Zero(fsm) \
    assert(getState(fsm) != NULL); \
    setTransition((fsm), "Zero"); \
    getState(fsm)->Zero(fsm); \
    setTransition((fsm), NULL);

#endif

/*
 * Local variables:
 *  buffer-read-only: t
 * End:
 */
